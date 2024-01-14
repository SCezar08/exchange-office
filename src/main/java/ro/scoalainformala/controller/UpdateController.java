package ro.scoalainformala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;
import ro.scoalainformala.utils.xml_parser.ListOfParsedCurrency;
import ro.scoalainformala.utils.xml_reader.Certificates;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

@Controller
public class UpdateController {

    private static final File OUTPUT_FILE_PATH = new File("E:\\Proiect\\bnr-rates.xml");


    // The CurrencyRepo is injected to interact with the data storage, typically a database, for currency information.
    private final CurrencyRepo currencyRepo;


    // Constructor for UpdateController, initializing it with the required CurrencyRepo.
    @Autowired
    public UpdateController(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }


    // Handles HTTP GET requests to "/update-database" endpoint.
    // Downloads the latest currency exchange rates XML data from the BNR website and saves it to a local file.
    // Parses the XML data to get the list of currencies.
    // Deletes existing currency data in the database, adds a default RON currency, and saves the updated currencies.
    // Adds the list of currencies to the model, along with a success message.
    // Returns the name of the view template, "/update-database".
    @GetMapping("/update-database")
    public String updateDatabase(Model model) {

        try {
            Certificates.trustAllCertificates();

            String xmlUrl = "https://www.bnr.ro/nbrfxrates.xml";

            URL url = new URL(xmlUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH)) {

                byte[] bytes = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(bytes, 0, 1024)) != -1) {
                    fileOutputStream.write(bytes, 0, bytesRead);
                }
            }
            Currency ron = new Currency("RON", 1.0, " (Leu Romanesc)");
            // Parse the XML and get the list of currencies
            List<Currency> currencies = ListOfParsedCurrency.parsedCurrency();


            // Save the updated currencies to the database
            currencyRepo.deleteAll(); // Remove existing data
            currencies.add(ron);
            currencies.sort(Comparator.comparing(Currency::getCurrencyName));
            currencyRepo.saveAll(currencies);

            // Add the list of currencies to the model
            model.addAttribute("currencies", currencies);
            model.addAttribute("message", "The database has been updated!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/update-database";
    }
}

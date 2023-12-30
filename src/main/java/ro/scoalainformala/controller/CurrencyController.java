package ro.scoalainformala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;
import ro.scoalainformala.service.CurrencyService;
import ro.scoalainformala.utils.xml_parser.ListOfParsedCurrency;
import ro.scoalainformala.utils.xml_reader.Certificates;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Controller
public class CurrencyController {

    private static final File OUTPUT_FILE_PATH = new File("E:\\Proiect\\bnr-rates.xml");
    private final CurrencyService service;
    private final CurrencyRepo currencyRepo;

    public CurrencyController(CurrencyService service, CurrencyRepo currencyRepo) {
        this.service = service;
        this.currencyRepo = currencyRepo;
    }


    @GetMapping("/currency")
    public String getAllCurrency(Model model) {
        List<Currency> currencies = service.getCurrency();
        model.addAttribute("currencies", currencies);
        return "currency";
    }
}
//    @GetMapping("/update-database")
//    public String updateDatabase() {
//
//        try {
//            Certificates.trustAllCertificates();
//
//            String xmlUrl = "https://www.bnr.ro/nbrfxrates.xml";
//
//            URL url = new URL(xmlUrl);
//            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//
//            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
//                 FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH)) {
//
//                byte[] bytes = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = in.read(bytes, 0, 1024)) != -1) {
//                    fileOutputStream.write(bytes, 0, bytesRead);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//}

//    @GetMapping("/update-database")
//    public String updateDatabase(Model model) {
//        List<Currency> parsedCurrencies = ListOfParsedCurrency.parsedCurrency();
//        currencyRepo.deleteAll();
//        currencyRepo.saveAll(parsedCurrencies);
//
//        List<Currency> currencies = currencyRepo.findAll();
//        model.addAttribute("currencies", currencies);
//
//        return "redirect:/currency";
//    }

package ro.scoalainformala.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.Comparator;
import java.util.List;

@Controller
public class CurrencyController {


    // The CurrencyService is injected to provide business logic related to currencies.
    // This service handles operations such as retrieving, updating, or processing currency-related data.
    private final CurrencyService service;

    // The CurrencyRepo is injected to interact with the data storage, typically a database, for currency information.
    // This repository is responsible for database operations like querying or updating currency data.
    private final CurrencyRepo currencyRepo;

    public CurrencyController(CurrencyService service, CurrencyRepo currencyRepo) {
        this.service = service;
        this.currencyRepo = currencyRepo;
    }


    // Handles HTTP GET requests to "/currency" endpoint.
    // Retrieves a list of currencies using the CurrencyService.
    // Adds the list of currencies to the model, making it available to the view.
    // Returns the name of the view template, in this case, "currency".
    @GetMapping("/currency")
    public String getAllCurrency(Model model) {
        List<Currency> currencies = service.getCurrency();
        model.addAttribute("currencies", currencies);
        return "currency";
    }
}
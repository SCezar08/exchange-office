package ro.scoalainformala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;
import ro.scoalainformala.service.CurrencyService;
import ro.scoalainformala.utils.xml_parser.ListOfParsedCurrency;

import java.util.List;

@Controller
public class CurrencyController {

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

    @GetMapping("/update-database")
    public String updateDatabase(Model model) {
        List<Currency> parsedCurrencies = ListOfParsedCurrency.parsedCurrency();
        currencyRepo.deleteAll();
        currencyRepo.saveAll(parsedCurrencies);

        List<Currency> currencies = currencyRepo.findAll();
        model.addAttribute("currencies", currencies);

        return "redirect:/currency";
    }
}

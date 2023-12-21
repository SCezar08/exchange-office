package ro.scoalainformala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.service.CurrencyService;

import java.util.List;


@Controller
public class ForeignToRonController {


    private final CurrencyService service;

    @Autowired
    public ForeignToRonController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/foreign-to-ron")
    public String displayReverseCurrency(Model model) {
        List<Currency> currencies = service.getCurrency();
        model.addAttribute("currencies", currencies);
        return "foreign-to-ron";
    }

    @RequestMapping("/foreign-to-ron")
    public String reverseConvert(@RequestParam("amount") String amount,
                                 @RequestParam("currencyOption") String selectedCurrency,
                                 Model model) {
        double originalAmount = Double.parseDouble(amount);
        double finalAmount = service.convertForeignToRon(originalAmount, selectedCurrency);

        model.addAttribute("originalAmount", originalAmount);
        model.addAttribute("finalAmount", finalAmount);
        model.addAttribute("selectedCurrency", selectedCurrency);
        return "foreign-to-ron";
    }
}

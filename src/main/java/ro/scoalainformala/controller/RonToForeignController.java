package ro.scoalainformala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.service.CurrencyService;

import java.util.List;

@Controller
public class RonToForeignController {

    private final CurrencyService service;

    @Autowired
    public RonToForeignController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/ron-to-foreign")
    public String displayCurrency(Model model) {
        List<Currency> currencies = service.getCurrency();
        model.addAttribute("currencies", currencies);
        return "ron-to-foreign";
    }

    @PostMapping("/ron-to-foreign")
    public String convert(@RequestParam("amount") String amount,
                          @RequestParam("currencyOption") String selectedCurrency,
                          Model model) {
        List<Currency> currencies = service.getCurrency();
        double originalAmount;

        try {
            originalAmount = Double.parseDouble(amount);
            if (originalAmount < 0.01) {
                model.addAttribute("error", "Amount must be greater than or equal to 0.01.");
                model.addAttribute("currencies", currencies);
                return "ron-to-foreign";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid amount format.");
            model.addAttribute("currencies", currencies);
            return "ron-to-foreign";
        }


        double finalAmount = service.convertRonToForeign(originalAmount, selectedCurrency);
        String selectedCurrencyFullName = Currency.getCurrencyFullName(selectedCurrency);

        model.addAttribute("currencies", currencies);
        model.addAttribute("originalAmount", originalAmount);
        model.addAttribute("finalAmount", finalAmount);
        model.addAttribute("selectedCurrency", selectedCurrency);
        model.addAttribute("selectedCurrencyFullName", selectedCurrencyFullName);
        return "ron-to-foreign";
    }
}

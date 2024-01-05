package ro.scoalainformala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.service.CurrencyService;

import java.util.List;

@Controller
public class ExchangeCalculatorController {

    private final CurrencyService service;

    public ExchangeCalculatorController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/exchange-calculator")
    public String displayCurrency(Model model) {
        List<Currency> currencies = service.getCurrency();
        model.addAttribute("currencies", currencies);
        return "exchange-calculator";
    }

    @PostMapping("/exchange-calculator")
    public String convert(@RequestParam("amount") String amount,
                          @RequestParam("fromCurrency") String fromCurrency,
                          @RequestParam("toCurrency") String toCurrency,
                          Model model) {
        List<Currency> currencies = service.getCurrency();
        double originalAmount;

        try {
            originalAmount = Double.parseDouble(amount);
            if (originalAmount < 0.01) {
                model.addAttribute("error", "Amount must be greater than or equal to 0.01!");
                model.addAttribute("currencies", currencies);
                return "exchange-calculator";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid amount format!");
            model.addAttribute("currencies", currencies);
            return "exchange-calculator";
        }


        double finalAmount = service.convertBetweenCurrencies(originalAmount, fromCurrency, toCurrency);
        String selectedCurrencyFullName = Currency.getCurrencyFullName(toCurrency);

        model.addAttribute("currencies", currencies);
        model.addAttribute("originalAmount", originalAmount);
        model.addAttribute("finalAmount", finalAmount);
        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("selectedCurrencyFullName", selectedCurrencyFullName);
        return "exchange-calculator";
    }
}

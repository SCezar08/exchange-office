package ro.scoalainformala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepo currencyRepo;

    @Autowired
    public CurrencyService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public List<Currency> getCurrency() {
        return currencyRepo.findAll();
    }

    public double convertRonToForeign(double amount, String selectedCurrency) {
        Currency currency = currencyRepo.findByCurrencyName(selectedCurrency);
        double exchangeRate = currency.getExchangeRate();
        BigDecimal bd = BigDecimal.valueOf(amount / exchangeRate).setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double convertForeignToRon(double amount, String selectedCurrency) {
        Currency currency = currencyRepo.findByCurrencyName(selectedCurrency);
        double exchangeRate = currency.getExchangeRate();
        BigDecimal bd = BigDecimal.valueOf(amount * exchangeRate).setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

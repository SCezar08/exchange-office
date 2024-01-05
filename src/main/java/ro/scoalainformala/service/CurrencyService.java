package ro.scoalainformala.service;

import jakarta.transaction.Transactional;
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

    public double convertBetweenCurrencies(double amount, String fromCurrency, String toCurrency) {
        Currency fromCurrencyObj = currencyRepo.findByCurrencyName(fromCurrency);
        Currency toCurrencyObj = currencyRepo.findByCurrencyName(toCurrency);

        double fromExchangeRate = fromCurrencyObj.getExchangeRate();
        double toExchangeRate = toCurrencyObj.getExchangeRate();

        // Convert the amount directly from the source currency to the target currency
        double finalAmount = (amount * fromExchangeRate) / toExchangeRate;

        BigDecimal bd = BigDecimal.valueOf(finalAmount).setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

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


    // The CurrencyRepo is injected to interact with the data storage, typically a database, for currency information.
    private final CurrencyRepo currencyRepo;


    // Constructor for CurrencyService, initializing it with the required CurrencyRepo.
    @Autowired
    public CurrencyService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }


    // Retrieves a list of all currencies from the database using the CurrencyRepo.
    public List<Currency> getCurrency() {
        return currencyRepo.findAll();
    }


    // Converts an amount from one currency to another using the exchange rates from the database.
    // The conversion is done by multiplying the amount by the ratio of the exchange rates between the source and target currencies.
    // The result is rounded to three decimal places for precision.
    public double convertBetweenCurrencies(double amount, String fromCurrency, String toCurrency) {
        // Retrieve Currency objects for the source and target currencies from the database
        Currency fromCurrencyObj = currencyRepo.findByCurrencyName(fromCurrency);
        Currency toCurrencyObj = currencyRepo.findByCurrencyName(toCurrency);

        // Get exchange rates for the source and target currencies
        double fromExchangeRate = fromCurrencyObj.getExchangeRate();
        double toExchangeRate = toCurrencyObj.getExchangeRate();

        // Convert the amount directly from the source currency to the target currency
        double finalAmount = (amount * fromExchangeRate) / toExchangeRate;

        // Round the final amount to three decimal places
        BigDecimal bd = BigDecimal.valueOf(finalAmount).setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

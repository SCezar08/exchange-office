package ro.scoalainformala;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;
import ro.scoalainformala.utils.xml_parser.ListOfParsedCurrency;

import java.util.Comparator;
import java.util.List;

@Configuration
public class CurrencyConfig {

    // This method creates a CommandLineRunner bean that is executed on application startup.
    @Bean
    CommandLineRunner commandLineRunner(
            CurrencyRepo currencyRepo) {
        return args -> {
            Currency ron = new Currency("RON", 1.0, " (Leu Romanesc)");

            // Retrieve the list of currencies parsed from some source
            List<Currency> parsedCurrencies = ListOfParsedCurrency.parsedCurrency();

            // Add the default RON currency to the list
            parsedCurrencies.add(ron);

            // Sort the list of currencies by currency name
            parsedCurrencies.sort(Comparator.comparing(Currency::getCurrencyName));

            // Save all currencies to the database using the injected CurrencyRepo
            currencyRepo.saveAll(parsedCurrencies);
        };
    }
}

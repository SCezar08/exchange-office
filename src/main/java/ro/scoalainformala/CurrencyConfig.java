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

    @Bean
    CommandLineRunner commandLineRunner(
            CurrencyRepo currencyRepo) {
        return args -> {
            Currency ron = new Currency("RON", 1.0, " (Leu Romanesc)");
            List<Currency> parsedCurrencies = ListOfParsedCurrency.parsedCurrency();
            parsedCurrencies.add(ron);

            parsedCurrencies.sort(Comparator.comparing(Currency::getCurrencyName));

            currencyRepo.saveAll(parsedCurrencies);
        };
    }
}


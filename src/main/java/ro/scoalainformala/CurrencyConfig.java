package ro.scoalainformala;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.scoalainformala.model.Currency;
import ro.scoalainformala.repo.CurrencyRepo;
import ro.scoalainformala.utils.xml_parser.ListOfParsedCurrency;

import java.util.List;

@Configuration
public class CurrencyConfig {

    @Bean
    CommandLineRunner commandLineRunner(CurrencyRepo currencyRepo) {
        return args -> {
            if (currencyRepo.count() == 0) {
                List<Currency> parsedCurrencies = ListOfParsedCurrency.parsedCurrency();
                currencyRepo.saveAll(parsedCurrencies);
            }
        };
    }
}

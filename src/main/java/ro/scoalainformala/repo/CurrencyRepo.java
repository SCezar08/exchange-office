package ro.scoalainformala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.scoalainformala.model.Currency;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    Currency findByCurrencyName(String currencyName);
}

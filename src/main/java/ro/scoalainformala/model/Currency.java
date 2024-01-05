package ro.scoalainformala.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.HashMap;
import java.util.Map;


@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String currencyName;
    private double exchangeRate;
    private String fullName;

    public Currency() {

    }

    public Currency(String currencyName, double exchangeRate, String fullName) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyName() {

        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }

    // Static map to store currency codes and their corresponding full names
    private static final Map<String, String> CURRENCY_FULL_NAMES = new HashMap<>();

    // Static block to initialize the map with currency code and full name pairs
    static {
        CURRENCY_FULL_NAMES.put("AED", " (United Arab Emirates Dirham)");
        CURRENCY_FULL_NAMES.put("AUD", " (Australian Dollar)");
        CURRENCY_FULL_NAMES.put("BGN", " (Bulgarian Lev)");
        CURRENCY_FULL_NAMES.put("BRL", " (Brazilian Rel)");
        CURRENCY_FULL_NAMES.put("CAD", " (Canadian Dollar)");
        CURRENCY_FULL_NAMES.put("CHF", " (Swiss Franc)");
        CURRENCY_FULL_NAMES.put("CNY", " (China Yuan/Renminbi)");
        CURRENCY_FULL_NAMES.put("CZK", " (Czech Koruna)");
        CURRENCY_FULL_NAMES.put("DKK", " (Danish Krone)");
        CURRENCY_FULL_NAMES.put("EGP", " (Egyptian Pound)");
        CURRENCY_FULL_NAMES.put("EUR", " (Euro)");
        CURRENCY_FULL_NAMES.put("GBP", " (Great Britain Pound)");
        CURRENCY_FULL_NAMES.put("HUF", " (Hungarian Forint)");
        CURRENCY_FULL_NAMES.put("INR", " (Indian Rupee)");
        CURRENCY_FULL_NAMES.put("JPY", " (Japanese Yen)");
        CURRENCY_FULL_NAMES.put("KRW", " (Korean Won)");
        CURRENCY_FULL_NAMES.put("MDL", " (Moldova Leu)");
        CURRENCY_FULL_NAMES.put("MXN", " (Mexican Peso)");
        CURRENCY_FULL_NAMES.put("NOK", " (Norwegian Kroner)");
        CURRENCY_FULL_NAMES.put("NZD", " (New Zealand Dollar)");
        CURRENCY_FULL_NAMES.put("PLN", " (Polish Zloty)");
        CURRENCY_FULL_NAMES.put("RON", " (Leu Romanesc)");
        CURRENCY_FULL_NAMES.put("RSD", " (Serbian Dinar)");
        CURRENCY_FULL_NAMES.put("RUB", " (Russian Rouble)");
        CURRENCY_FULL_NAMES.put("SEK", " (Swedish Krona)");
        CURRENCY_FULL_NAMES.put("THB", " (Thai Baht)");
        CURRENCY_FULL_NAMES.put("TRY", " (Turkish Lira)");
        CURRENCY_FULL_NAMES.put("UAH", " (Ukraine Hryvnia)");
        CURRENCY_FULL_NAMES.put("USD", " (USA Dollar)");
        CURRENCY_FULL_NAMES.put("XAU", " (Gold (oz))");
        CURRENCY_FULL_NAMES.put("XDR", " (Special Drawing Rights)");
        CURRENCY_FULL_NAMES.put("ZAR", " (South African Rand)");
    }

    // Method to retrieve the full name of a given currency code
    public static String getCurrencyFullName(String currencyCode) {
        // Using getOrDefault to handle cases where the currency code is not found in the map
        return CURRENCY_FULL_NAMES.getOrDefault(currencyCode, currencyCode);
    }
}

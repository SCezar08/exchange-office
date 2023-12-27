package ro.scoalainformala.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String currencyName;
    private double exchangeRate;

    public Currency() {

    }

    public Currency(String currencyName, double exchangeRate) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
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

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }

//    private String getCurrencyFullName(String currency) {
//
//        switch (currency) {
//            case "AED": return "United Arab Emirates Dirham";
//            case "AUD": return "Australian Dollar";
//            case "BGN": return "Bulgarian Lev";
//            case "BRL": return "Brazilian Rel";
//            case "CAD": return "Canadian Dollar";
//            case "CHF": return "Swiss Franc";
//            case "CNY": return "China Yuan/Renminbi";
//            case "CZK": return "Czech Koruna";
//            case "DKK": return "Danish Krone";
//            case "EGP": return "Egyptian Pound";
//            case "EUR": return "Euro";
//            case "GBP": return "Great Britain Pound";
//            case "HUF": return "Hungarian Forint";
//            case "INR": return "Indian Rupee";
//            case "JPY": return "Japanese Yen";
//            case "KRW": return "Korean Won";
//            case "MDL": return "Moldova Leu";
//            case "MXN": return "Mexican Peso";
//            case "NOK": return "Norwegian Kroner";
//            case "NZD": return "New Zealand Dollar";
//            case "PLN": return "Polish Zloty";
//            case "RSD": return "Serbian Dinar";
//            case "RUB": return "Russian Rouble";
//            case "SEK": return "Swedish Krona";
//            case "THB": return "Thai Baht";
//            case "TRY": return "Turkish Lira";
//            case "UAH": return "Ukraine Hryvnia";
//            case "USD": return "USA Dollar";
//            case "XAU": return "Gold (oz)";
//            case "XDR": return "Special Drawing Rights";
//            case "ZAR": return "South African Rand";
//
//            default: return currency; // Return the original code if no match is found
//        }
//    }

}

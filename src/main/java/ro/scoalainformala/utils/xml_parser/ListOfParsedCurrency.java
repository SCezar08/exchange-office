package ro.scoalainformala.utils.xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ro.scoalainformala.model.Currency;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfParsedCurrency {

    private static final String FILENAME = "E:\\Proiect\\bnr-rates.xml";

    public static List<Currency> parsedCurrency() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        List<Currency> currencies = new ArrayList<>();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));

            NodeList list = doc.getElementsByTagName("Rate");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String currencyName = element.getAttribute("currency");
                    double exchangeRate = Double.parseDouble(element.getTextContent());

                    Currency currency = new Currency(currencyName, exchangeRate);
                    currencies.add(currency);

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}

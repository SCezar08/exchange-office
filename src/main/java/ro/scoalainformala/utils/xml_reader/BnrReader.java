package ro.scoalainformala.utils.xml_reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BnrReader {

    private static final String BNR_URL = "https://www.bnr.ro/nbrfxrates.xml";

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","./src/main/resources/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","mypass");
        try {
            URL url = new URL(BNR_URL);
            InputStream is = url.openStream();
            int b = 0;
            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

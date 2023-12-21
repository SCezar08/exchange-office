package ro.scoalainformala.utils.xml_reader;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class XMLReader {

    private static final File OUTPUT_FILE_PATH = new File("E:\\Proiect\\bnr-rates.xml");

    public static void main(String[] args) {
        try {
            Certificates.trustAllCertificates();

            String xmlUrl = "https://www.bnr.ro/nbrfxrates.xml";

            URL url = new URL(xmlUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH)) {

                byte[] bytes = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(bytes, 0, 1024)) != -1) {
                    fileOutputStream.write(bytes, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ro.scoalainformala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.scoalainformala.utils.xml_reader.Certificates;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Controller
public class UpdateController {

    private static final File OUTPUT_FILE_PATH = new File("E:\\Proiect\\bnr-rates.xml");

    @GetMapping("/update-database")
    public String updateDatabase(Model model) {

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
            model.addAttribute("message", "The database has been updated!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/update-database";
    }
}

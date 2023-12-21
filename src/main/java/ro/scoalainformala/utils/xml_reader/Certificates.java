package ro.scoalainformala.utils.xml_reader;

import javax.net.ssl.HttpsURLConnection;

public class Certificates {
    // Method to trust all SSL certificates (for testing purposes, not recommended for production)
    public static void trustAllCertificates() {
        // Set a custom hostname verifier that accepts all hostnames
        try {
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            // Create an array of TrustManagers with a single X509TrustManager that accepts all certificates
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
                    new javax.net.ssl.X509TrustManager() {
                        // Method to return an array of accepted issuers (null for accepting all issuers)
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        // Method to validate the client certificate (not implemented, accepts all clients)
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        // Method to validate the server certificate (not implemented, accepts all servers)
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Create an SSLContext and initialize it with the TrustManagers
            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Set the default SSLSocketFactory to use the custom SSLContext
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            // Print any exceptions that occur during the process
            e.printStackTrace();
        }
    }
}

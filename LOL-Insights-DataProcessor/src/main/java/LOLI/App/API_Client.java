package LOLI.App;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;



public class API_Client {
    public static HttpResponse<String> response;
    private static final String API_URL = "https://127.0.0.1:2999/liveclientdata/allgamedata";
    public static HttpClient client;
    public static HttpRequest request;

    public API_Client() {
        System.out.println("Creating API Client");
        try {
            // Create a TrustManager that ignores certificate validation
            TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
            };

            // Initialize SSL context with the TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());


            // Create HttpClient with the custom SSL context
            client = HttpClient.newBuilder()
                    .sslContext(sslContext) // Bypass SSL verification
                    .build();

            // Build the request
            request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Accept", "application/json")
                    .build();

//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RequestApiData(){
        // Send the request
        try{
            //System.out.println("Before" + response);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("After" + response);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HttpResponse<String> getResponse(){
        return response;
    }

}

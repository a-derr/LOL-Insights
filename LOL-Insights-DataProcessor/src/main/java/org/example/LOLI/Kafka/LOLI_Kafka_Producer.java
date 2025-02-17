package org.example.LOLI.Kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

public class LOLI_Kafka_Producer {
    private static final String API_URL = "https://127.0.0.1:2999/liveclientdata/allgamedata";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        //ingest data from Riot Games Live Client Data API
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
            HttpClient client = HttpClient.newBuilder()
                    .sslContext(sslContext) // Bypass SSL verification
                    .build();

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Accept", "application/json")
                    .build();

            // Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print response
            if (response.statusCode() == 200 && response.body() != null) {
                System.out.println("Live Client Data:");
                String responseBody = new String(response.body().getBytes());
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                System.out.println("Fetched Data: " + jsonNode.toPrettyString());

            } else {
                System.err.println("Failed to fetch data. HTTP Status: " + response.statusCode());
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException | InterruptedException e) {
            e.printStackTrace();
        }



//        String bootstrapServer = "localhost:9092";
//
//        Properties properties = new Properties();
//        properties.setProperty(PropertyConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // Create Producer

        //organize data into topics for kafka ingestion
            // Game_events - Kills, Objectives, item purchases
            // player_stats - CS, K/D/A, gold, ability cooldowns
            // team_info - objectives taken, turrets destroyed

        // Format data in JSON and serialize messages
            // Makes processing more efficient

        // Send messages to kafka

    }
}



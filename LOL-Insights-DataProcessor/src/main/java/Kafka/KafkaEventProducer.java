package Kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

import LOLI.App.API_Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaEventProducer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final API_Client apiClient = new API_Client();

    // Kafka Producer instance
    private static KafkaProducer<String, String> producer;

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {

        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "riot-live-data-stream");
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        streamProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Specify the key and value SerDes (use StringSerde as an example)
        streamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        streamProperties.put(StreamsConfig.TOPOLOGY_OPTIMIZATION_CONFIG, StreamsConfig.NO_OPTIMIZATION);

        // Set up Kafka Producer properties
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", "localhost:9092");
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(producerProperties);

        apiClient.RequestApiData();

        // Get the data from the response
        String responseBody = new String(API_Client.response.body().getBytes());

        // Parse and print the fetched data
        JsonNode rootNode = objectMapper.readTree(responseBody);
//        System.out.println("Fetched Data: " + rootNode.toPrettyString());

        // Extract the relevant parts of the JSON and print them
        String activePlayerData = rootNode.path("activePlayer").toString();
        String allPlayerData = rootNode.path("allPlayers").toString();
        String gameEventData = rootNode.path("events").path("Events").toString();
        String gameData = rootNode.path("gameData").toString();

//        StreamsBuilder builder = new StreamsBuilder();
        System.out.println("ACTIVE PLAYER :" + activePlayerData);
        System.out.println("ALL PLAYER :" + allPlayerData);
        System.out.println("GAME EVENT :" + gameEventData);
        System.out.println("GAME :" + gameData);
        //ingest data from Riot Games Live Client Data API

//        Topology topology = builder.build();
//        KafkaStreams streams = new KafkaStreams(topology, streamProperties);
//        streams.start();


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



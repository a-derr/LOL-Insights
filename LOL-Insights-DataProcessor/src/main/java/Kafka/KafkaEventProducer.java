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

    // Kafka Producer instance
    private static KafkaProducer<String, String> producer;

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {




//
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



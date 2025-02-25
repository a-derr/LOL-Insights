package LOLI.App;

import Model.Game;
import Model.PlayerData.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LOLI {
    public static Game currGame;
    public static Player activePlayer;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final API_Client apiClient = new API_Client();

    //private static KafkaProducer<String, String> producer = new KafkaProducer<>(new Properties());
    public static void main(String[] args) {



        ScheduledExecutorService producerScheduler = Executors.newScheduledThreadPool(1);
        producerScheduler.scheduleAtFixedRate(LOLI::fetchData, 0, 3000, TimeUnit.MILLISECONDS);

        ScheduledExecutorService consumerScheduler = Executors.newScheduledThreadPool(1);
        consumerScheduler.schedule(LOLI::ConsumeRecord, 15000, TimeUnit.MILLISECONDS);
    }

    public LOLI(){
        activePlayer = new Player();
        currGame = new Game();

    }

    public Player getActivePlayer() {
        return activePlayer;
    }
    private void setActivePlayerData() {

    }

    private static void fetchData(){
     try{
         apiClient.RequestApiData();
         clearConsole();
         String responseBody = new String(API_Client.getResponse().body());

         JsonNode rootNode = objectMapper.readTree(responseBody);

         String activePlayerData = rootNode.path("activePlayer").toString();
         String allPlayerData = rootNode.path("allPlayers").toString();
         String gameEventData = rootNode.path("events").path("Events").toString();
         String gameData = rootNode.path("gameData").toString();

//         System.out.println("ACTIVE PLAYER :" + activePlayerData);
//         System.out.println("ALL PLAYER :" + allPlayerData);
//         System.out.println("GAME EVENT :" + gameEventData);
         System.out.println("GAME :" + gameData);

         ProduceRecord(gameData);



     } catch (Exception e) {
         System.err.println("Error fetching data: " + e.getMessage());
     }
    }

    private static void ConsumeRecord(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-app-group");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of("game-data"));

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));

            for(ConsumerRecord<String, String> record : records){
                System.out.println("RECORD DATA: " + record.value());
            }
        }
    }


    private static void ProduceRecord(String data){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> msg = new ProducerRecord<>("game-data", data);

        producer.send(msg);

        producer.flush();
        producer.close();
    }

    private static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");  // ANSI escape code for clearing console
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Failed to clear console: " + e.getMessage());
        }
    }
}

package Flink;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class FlinkTest {
    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool paramTool = ParameterTool.fromPropertiesFile(FlinkTest.class.getResourceAsStream("/application.properties"));

//                fromPropertiesFile("src/main/resources/application.properties");
        Configuration config = paramTool.getConfiguration();

        String broker = config.getString(ConfigOptions.key("loli.app.kafkaBroker").noDefaultValue());

        KafkaSource<String> kSource = KafkaSource.<String>builder()
                .setBootstrapServers(broker)
                .setTopics("game-data")
                .setGroupId("test-app-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();



        DataStream<String> stream = env
                .fromSource(
                        kSource,
                        WatermarkStrategy.noWatermarks(),
                        "kafka-source"
                );

        stream.map(record -> {
                    System.out.println("Received record: " + record);
                    return record;
                });

        stream.print();

        env.execute("flink-Kafka-connector-test");
    }
}

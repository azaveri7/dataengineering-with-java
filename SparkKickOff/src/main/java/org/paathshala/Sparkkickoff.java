package org.paathshala;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.streaming.kafka010.OffsetRange;

import java.util.*;

public class Sparkkickoff {

    private static final String APPLICATION_NAME = "Sparkkickoff";
    private static final String HADOOP_HOME_DIR_VALUE = "E:\\notes\\Hadoop\\winutils";
    private static final String RUN_LOCAL_WITH_AVAILABLE_CORES = "local[*]";

    private static final Map<String, Object> KAFKA_CONSUMER_PROPERTIES;

    private static final String KAFKA_BROKERS = "localhost:9092";
    private static final String KAFKA_OFFSET_RESET_TYPE = "latest";
    private static final String KAFKA_GROUP = "meetupGroup";
    private static final String KAFKA_TOPIC = "meetupTopic";
    private static final String MONGODB_OUTPUT_URI = "mongodb://localhost/meetupDB.rsvps";
    private static final OffsetRange[] offset =
            // topic, partition, inclusive starting offset, exclusive ending offset
            { OffsetRange.create(KAFKA_TOPIC, 0, 0 , 100)};

    static {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_GROUP);
        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KAFKA_OFFSET_RESET_TYPE);
        kafkaProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        KAFKA_CONSUMER_PROPERTIES = Collections.unmodifiableMap(kafkaProperties);
    }


    public static void main(String[] args){
        System.setProperty("hadoop.home.dir", HADOOP_HOME_DIR_VALUE);

        final SparkConf conf = new SparkConf()
                .setAppName(APPLICATION_NAME)
                .setMaster(RUN_LOCAL_WITH_AVAILABLE_CORES)
                .set("spark.mongodb.output.uri", MONGODB_OUTPUT_URI);

        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        // business logic



        //KafkaUtils

        sparkContext.stop();
        sparkContext.close();

    }

}

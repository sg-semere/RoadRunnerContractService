package org.safeguard.contract.stream;

import static org.apache.kafka.streams.StreamsConfig.*;
import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class StreamConfig {
    private static final Logger log = LoggerFactory.getLogger(StreamConfig.class);
    /*
    * define create properties
    * @return Properties
    * */
    static Properties streamConfig(String bootstrapServer) {
        log.info("Building kafka stream config for contract-validator");
        Properties streamConfiguration = new Properties();
        streamConfiguration.put(APPLICATION_ID_CONFIG, "contract-validator");
        streamConfiguration.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamConfiguration.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamConfiguration.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        streamConfiguration.put(STATE_DIR_CONFIG, "/Users/sghebrec/Documents/kafkaData");

        return streamConfiguration;
    }
}

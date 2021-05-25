package org.safeguard.contract.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.apache.kafka.streams.StreamsConfig.*;

public class StreamConfig {
    private static final Logger log = LoggerFactory.getLogger(StreamConfig.class);

    static Properties streamConfig(String appId, String bootstrapServer){
        log.info("Building kafka stream config for {}", appId);
        Properties streamConfiguration = new Properties();
        streamConfiguration.put(APPLICATION_ID_CONFIG, appId);
        streamConfiguration.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamConfiguration.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamConfiguration.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        return streamConfiguration;
    }
}

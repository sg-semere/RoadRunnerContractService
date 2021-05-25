package org.safeguard.contract;

import org.safeguard.contract.stream.StreamContractValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ContractValidatorMain {
    private static final Logger log = LoggerFactory.getLogger(ContractValidatorMain.class);

    public static void main(String[] args) {
        log.info("starting stream with bootstrap server {}");

        var streamsApp = StreamContractValidator.buildStreamsApp("contract-validator", "http://localhost:9092");

        Runtime.getRuntime().addShutdownHook(new Thread(streamsApp::close));
//        Runtime.getRuntime().addShutdownHook(new Thread(streamsApp::stop));
//        KafkaHelpers.onError(streamsApp).thenAcceptAsync(o -> {
//            log.error("Application encountered an error. shutting down.");
//            streamsApp.close(Duration.ofSeconds(5));
//            streamsApp.stop(Duration.ofSeconds(5));
//        });

        streamsApp.start();
        streamsApp.close();

    }
}
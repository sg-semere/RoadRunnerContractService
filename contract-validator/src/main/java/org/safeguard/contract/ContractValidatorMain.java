package org.safeguard.contract;

import org.safeguard.contract.stream.StreamContractValidator;
import org.safeguard.contract.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ContractValidatorMain {
    private static final Logger log = LoggerFactory.getLogger(ContractValidatorMain.class);

    public static void main(String[] args) {
        log.info("starting stream with bootstrap server {}");

        var streamsApp = StreamContractValidator.buildStreamsApp("http://localhost:9092");

        Runtime.getRuntime().addShutdownHook(new Thread(streamsApp::close));
        StreamUtils.onError(streamsApp).thenAcceptAsync(o -> {
            log.error("Application encountered an error. shutting down.");
            streamsApp.close(Duration.ofSeconds(5));
        });

        streamsApp.start();
    }
}
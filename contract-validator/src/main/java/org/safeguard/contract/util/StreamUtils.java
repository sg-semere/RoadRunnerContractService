package org.safeguard.contract.util;

import org.apache.kafka.streams.KafkaStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class StreamUtils {
    private static final Logger log = LoggerFactory.getLogger(StreamUtils.class);

    public static boolean isNull(String key, String value) {
        return value == null;
    }

    public static CompletableFuture<Object> onError(KafkaStreams streams) {
        var stateFuture = new CompletableFuture<KafkaStreams.State>();
        streams.setStateListener((newState, oldState) -> {
            if (!stateFuture.isDone() && (newState == KafkaStreams.State.ERROR
                    || newState == KafkaStreams.State.NOT_RUNNING)) {
                log.error("Streams app entered a bad state: " + newState + "");
                stateFuture.complete(newState);
            }
        });

        var exceptionFuture = new CompletableFuture<Throwable>();
        streams.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            log.error("Caught unhandled exception in thread [" + t + " ]", e);
            exceptionFuture.complete(e);
        });

        return CompletableFuture.anyOf(stateFuture, exceptionFuture);
    }
}

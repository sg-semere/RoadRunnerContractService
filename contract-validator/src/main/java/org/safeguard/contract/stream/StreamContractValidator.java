package org.safeguard.contract.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.safeguard.contract.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamContractValidator {
    private static final Logger log = LoggerFactory.getLogger(StreamContractValidator.class);

    public static KafkaStreams buildStreamsApp(String bootstrapServer) {
        var topology = buildTopology();
        var streamsConfig = StreamConfig.streamConfig(bootstrapServer);
        return new KafkaStreams(topology, streamsConfig);
    }

    static Topology buildTopology() {
        var builder = new StreamsBuilder();
        addTopologyForType(builder, "saveContract");

        return builder.build();
    }

    private static void addTopologyForType(StreamsBuilder builder, String topic) {
        var inputStream = builder.<String, String>stream(topic,
                Consumed.with(
                        Serdes.String(),
                        Serdes.String()
                ));
        inputStream
                .filter(StreamUtils::isNull)
                .peek((k, v) -> System.out.printf("contract: %s, %s, %s%n", k, v))
                .to("validatedTopic", Produced.with(Serdes.String(), Serdes.String()));
    }

}

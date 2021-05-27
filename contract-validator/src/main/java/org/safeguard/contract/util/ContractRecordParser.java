package org.safeguard.contract.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContractRecordParser {
    private static final Logger log = LoggerFactory.getLogger(ContractRecordParser.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> getJsonMap(String input) {
        System.out.println(input);
        if (input == null || input.equals("")) {
            return new LinkedHashMap<>();
        } else {
            try {
                return mapper.readValue(input, Map.class);
            } catch (JsonProcessingException e){
                return null;
            }
        }
    }

}

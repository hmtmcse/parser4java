package com.hmtmcse.parser4java;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmtmcse.parser4java.common.Parser4JavaException;

import java.util.LinkedHashMap;

public class JsonProcessor {

    public String klassToString(Object data) throws Parser4JavaException {
        return klassToString(data, false);
    }

    public String klassToString(Object data, Boolean isPrettify) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            if (isPrettify) {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            } else {
                return mapper.writeValueAsString(data);
            }
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

    public String objectToJsonRaw(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

    public String objectToJson(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

    public LinkedHashMap objectToMap(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            return mapper.convertValue(data, LinkedHashMap.class);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

    public LinkedHashMap stringToMap(String data) throws Parser4JavaException {
        return objectFromText(data, LinkedHashMap.class);
    }

    public <T> T objectFromText(String text, Class<T> klass) throws Parser4JavaException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return klass.cast(objectMapper.readValue(text, klass));
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

}

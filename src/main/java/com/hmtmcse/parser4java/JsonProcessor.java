package com.hmtmcse.parser4java;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmtmcse.parser4java.common.Parser4JavaException;

import java.util.LinkedHashMap;

public class JsonProcessor {

    public String klassToString(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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

}

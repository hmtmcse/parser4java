package com.hmtmcse.parser4java;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectProcessor {

    public static  <T> T mapToObject(Map map, Class<T> klass){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(map, klass);
    }

}

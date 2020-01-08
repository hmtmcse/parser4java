package com.hmtmcse.parser4java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectProcessor {

    public static  <T> T mapToObject(Map map, Class<T> klass){
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, klass);
    }

}

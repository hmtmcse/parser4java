package com.hmtmcse.parser4java;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hmtmcse.parser4java.common.Parser4JavaException;

public class YamlProcessor {

    public String klassToString(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }


}

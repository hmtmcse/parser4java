package com.hmtmcse.parser4java;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hmtmcse.fileutil.data.TextFileData;
import com.hmtmcse.fileutil.text.TextFile;
import com.hmtmcse.parser4java.common.Parser4JavaException;

import java.util.LinkedHashMap;

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

    public String klassToStringRaw(Object data) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

    public LinkedHashMap ymlAsMap(String location) throws Parser4JavaException {
        return ymlAsNestedKlass(location, LinkedHashMap.class);
    }

    public LinkedHashMap ymlAsMapFromString(String content) throws Parser4JavaException {
        return ymlAsNestedKlassFromString(content, LinkedHashMap.class);
    }

    public <T> T ymlAsNestedKlassFromString(String content, Class<T> klass) throws Parser4JavaException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(content, klass);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }


    public <T> T ymlAsNestedKlass(String location, Class<T> klass) throws Parser4JavaException {
        try {
            TextFile textFile = new TextFile();
            TextFileData textFileData = textFile.fileToString(location);
            return ymlAsNestedKlassFromString(textFileData.text, klass);
        } catch (Exception e) {
            throw new Parser4JavaException(e.getMessage());
        }
    }

}

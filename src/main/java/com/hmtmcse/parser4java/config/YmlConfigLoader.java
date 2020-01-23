package com.hmtmcse.parser4java.config;

import com.hmtmcse.parser4java.YamlProcessor;
import com.hmtmcse.parser4java.common.Parser4JavaException;

import java.util.LinkedHashMap;

public class YmlConfigLoader {

    private static volatile YamlProcessor instance;
    private static volatile LinkedHashMap<String, Object> ymlLinkedHashMap;

    private YmlConfigLoader() {}

    public static LinkedHashMap<String, Object> loadYmlAsMap(String location) throws Parser4JavaException {
        if (instance == null) {
            synchronized (YamlProcessor.class) {
                if (instance == null) {
                    instance = new YamlProcessor();
                    ymlLinkedHashMap = instance.ymlAsMap(location);
                }
            }
        }
        return ymlLinkedHashMap;
    }


    public static Object getMapValue(String key, String defaultValue){
        if (ymlLinkedHashMap != null && ymlLinkedHashMap.get(key) != null){
            return ymlLinkedHashMap.get(key);
        }
        return defaultValue;
    }

    public static Object getMapNestedValue(String location, String defaultValue, String... keys) {
        try {
            YamlProcessor _yamlProcessor = new YamlProcessor();
            return getMapNestedValue(_yamlProcessor.ymlAsMap(location), defaultValue, keys);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Object getMapNestedValue(LinkedHashMap<String, Object> ymlMap, String defaultValue, String... keys) {
        if (ymlMap != null) {
            LinkedHashMap<String, Object> temp = ymlMap;
            for (String key : keys) {
                if (temp.get(key) == null) {
                    return defaultValue;
                } else if (temp.get(key) instanceof LinkedHashMap) {
                    temp = (LinkedHashMap<String, Object>) temp.get(key);
                } else {
                    return temp.get(key);
                }
            }
        }
        return defaultValue;
    }

    public static Object getMapNestedValue(String defaultValue, String... keys) {
        return getMapNestedValue(ymlLinkedHashMap, defaultValue, keys);
    }

    public static String getMapNestedValueAsString(String defaultValue, String ...keys){
        Object object = getMapNestedValue(defaultValue, keys);
        if (object != null){
            return object.toString();
        }
        return null;
    }


    public static Integer getMapNestedValueAsInteger(String defaultValue, String ...keys){
        String object = getMapNestedValueAsString(defaultValue, keys);
        if (object != null){
            return Integer.valueOf(object);
        }
        return null;
    }
}

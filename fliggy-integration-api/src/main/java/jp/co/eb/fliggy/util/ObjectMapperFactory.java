package jp.co.eb.fliggy.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
    volatile private static ObjectMapper instance = null;

    private ObjectMapperFactory() {
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            synchronized (ObjectMapperFactory.class) {
                if (instance == null) {
                    instance = new ObjectMapper();
                }
            }
        }
        return instance;
    }
}

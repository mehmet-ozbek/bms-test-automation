package com.bengi.bms.core;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Config file not found!", e);
        }
    }

    private ConfigReader() {}

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
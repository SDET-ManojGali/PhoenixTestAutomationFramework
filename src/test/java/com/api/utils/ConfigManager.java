package com.api.utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static Properties prop = new Properties();
    private static String path ="config/config.properties";

    private ConfigManager(){

    }

    static {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if(path==null){
            throw new RuntimeException("Cannot find the file path: "+path);
        }
        try {
            prop.load(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}

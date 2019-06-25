package com.yh.spring.ssm.sendMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ZabbixConfig {

    private static Properties properties;

    public static Properties build(String path){
        if (properties == null){
            synchronized (ZabbixConfig.class) {
                if (properties == null) {
                    properties = new Properties();
                }
                try {
                    properties.load(new FileInputStream(new File(path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static Properties build1(String path) {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream(new File(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static Integer getInteger(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    public static Double getDouble(String key) {
        return Double.valueOf(properties.getProperty(key));
    }
}

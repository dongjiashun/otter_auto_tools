package com.yh.spring.ssm.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version :1.0
 * CREATE TIME :2017/12/6 11:41
 * @authro :dongjs
 */
public class ConfigUtil {

        private static Properties properties;

        public static Properties build1(String path) throws Exception {

            if(properties == null){
                InputStream inputStream = new  FileInputStream(new File(path));
                properties = new Properties();
                properties.load(inputStream);
            }
            return  properties;
        }


        public static Properties build(String path) throws Exception{
            synchronized (ConfigUtil.class){
                if(properties == null){
                    InputStream inputStream = new  FileInputStream(new File(path));
                    properties = new Properties();
                    properties.load(inputStream);
                }
            }
            return  properties;
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

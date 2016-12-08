package com.cdboo.business.util;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Created by houyi on 2016/12/7.
 */
public class Config implements Serializable{

    private static ResourceBundle rb = ResourceBundle.getBundle("cdboo");

    private static Config config = new Config();

    public synchronized static Config getConfig() {
        return config;
    }

    public static String getResource(String key) {
        return rb.getString(key);
    }



}

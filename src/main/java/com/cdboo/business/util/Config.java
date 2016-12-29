package com.cdboo.business.util;

import java.io.*;
import java.util.ResourceBundle;

/**
 * Created by houyi on 2016/12/7.
 */
public class Config implements Serializable {
    public static final String USER_DATA_FILE = "CDBOOPlayer.dat";
    public static final File USER_DATA_PATH = new File(System.getProperty("user.home") + File.separator + ".CDBOOPlayer");
    private static ResourceBundle rb = ResourceBundle.getBundle("cdboo");

    private static Config config = new Config();

    public synchronized static Config getConfig() {
        return config;
    }

    public static String getResource(String key) {
        return rb.getString(key);
    }

    public static boolean loadUserData() {
        ObjectInputStream ois = null;
        try {
            if (!USER_DATA_PATH.exists()) {
                USER_DATA_PATH.mkdirs();
            }
            ois = new ObjectInputStream(new FileInputStream(new File(Config.USER_DATA_PATH, USER_DATA_FILE)));
            config = (Config) ois.readObject();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {

            }
        }
    }

    public static void saveUserData() {
        try {
            if (!Config.USER_DATA_PATH.exists()) {
                Config.USER_DATA_PATH.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream((new File(Config.USER_DATA_PATH, USER_DATA_FILE + ".dat")));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(config);
            oos.flush();
            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

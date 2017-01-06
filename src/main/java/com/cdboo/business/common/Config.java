package com.cdboo.business.common;

import java.io.*;
import java.util.Date;

/**
 * Created by houyi on 2016/12/7.
 */
public class Config implements Serializable {
    public static final String USER_DATA_FILE = "CDBOOPlayer.dat";
    public static final File USER_DATA_PATH = new File(System.getProperty("user.home") + File.separator + ".CDBOOPlayer");

    private String userName;//用户姓名
    private String shopOwnerName;//店长姓名

    private Date businessHoursBegin;//营业时间开始
    private Date businessHoursEnd;//营业时间结束

    private String address;//门店地址
    private String photo;//用户头像

    private Date serviceTimeBegin; //用户服务时间开始时间，就是用户买了多久的会员就能用多久
    private Date serviceTimeEnd; //用户服务时间结束时间

    private static Config config = null;

    public synchronized static Config getConfigInstance() {
        if(config == null){
            config = new Config();
            loadUserData();
        }
        return config;
    }

    public static boolean loadUserData() {
        ObjectInputStream ois = null;
        try {
            if (!USER_DATA_PATH.exists()) {
                USER_DATA_PATH.mkdirs();
            }
            File file = new File(Config.USER_DATA_PATH, USER_DATA_FILE);
            if(file.exists()){
                ois = new ObjectInputStream(new FileInputStream(file));
                config = (Config) ois.readObject();
            }
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
            FileOutputStream fout = new FileOutputStream((new File(Config.USER_DATA_PATH, USER_DATA_FILE)));
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(config);
            oos.flush();
            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public Date getBusinessHoursBegin() {
        return businessHoursBegin;
    }

    public void setBusinessHoursBegin(Date businessHoursBegin) {
        this.businessHoursBegin = businessHoursBegin;
    }

    public Date getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(Date businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getServiceTimeBegin() {
        return serviceTimeBegin;
    }

    public void setServiceTimeBegin(Date serviceTimeBegin) {
        this.serviceTimeBegin = serviceTimeBegin;
    }

    public Date getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    public void setServiceTimeEnd(Date serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }

}

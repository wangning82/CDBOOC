package com.cdboo.business.model;

import java.io.Serializable;

/**
 * Created by houyi on 2017/2/17 0017.
 * 客户端保存插播计划时使用，用于绑定页面元素
 */
public class SpotModel implements Serializable {
    private String id;
    private String startTime;
    private String intervalTime;
    private String cycleTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(String cycleTimes) {
        this.cycleTimes = cycleTimes;
    }
}

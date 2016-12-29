package com.cdboo.business.model;

import java.io.Serializable;

/**
 * 用户音乐信息
 */
public class RestMusic implements Serializable {

    private static final long serialVersionUID = 1L;

    private String musicNo;        // 音乐编号
    private String musicOwner;        // 音乐拥有者类型
    private String musicName;        // 音乐名称
    private String actor;        // 艺人
    private String special;        // 专辑
    private String volume;        // 音量
    private String status;        // 状态（暂时没用）
    private String path;        // 音乐路径

    public String getMusicNo() {
        return musicNo;
    }

    public void setMusicNo(String musicNo) {
        this.musicNo = musicNo;
    }

    public String getMusicOwner() {
        return musicOwner;
    }

    public void setMusicOwner(String musicOwner) {
        this.musicOwner = musicOwner;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

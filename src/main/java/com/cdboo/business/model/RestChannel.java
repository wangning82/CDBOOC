package com.cdboo.business.model;


import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 用户频道信息
 */
public class RestChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String channelNo; // 频道编号
    private String channelName; // 频道名称
    private String photoPath; // 频道图片
    private String themeType; // 风格类型
    private String themeConcreteType; // 风格类型明细
    private String channelVersion; // 频道版本
    private String musicStyle; // 音乐风格
    private String site; // 场所
    private String speed; // 速度
    private String voice; // 人声
    private String element; // 元素
    private String emotion; // 情绪
    private String instrument; // 乐器
    private String status; // 状态（暂时没用）
    private String channelType; // 频道类型(0子频道,1组合频道,2插播频道)

    //如果是子频道，该集合有对应music的信息
    private List<RestMusic> musicList = Lists.newArrayList();

    //如果是组合频道，该集合有值
    private List<RestChannel> childChannelList = Lists.newArrayList();

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getThemeType() {
        return themeType;
    }

    public void setThemeType(String themeType) {
        this.themeType = themeType;
    }

    public String getThemeConcreteType() {
        return themeConcreteType;
    }

    public void setThemeConcreteType(String themeConcreteType) {
        this.themeConcreteType = themeConcreteType;
    }

    public String getChannelVersion() {
        return channelVersion;
    }

    public void setChannelVersion(String channelVersion) {
        this.channelVersion = channelVersion;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public List<RestMusic> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<RestMusic> musicList) {
        this.musicList = musicList;
    }

    public List<RestChannel> getChildChannelList() {
        return childChannelList;
    }

    public void setChildChannelList(List<RestChannel> childChannelList) {
        this.childChannelList = childChannelList;
    }
}

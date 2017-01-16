package com.cdboo.business.entity;


import com.cdboo.business.common.Constants;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * 用户频道信息
 */
@Entity
@Table(name = "cdboo_channel")
public class RestChannel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "channel_id")
    private long id;

    @Column(name = "channel_NO")
    private String channelNo; // 频道编号

    @Column(name = "channel_name")
    private String channelName; // 频道名称

    @Column(name = "photoPath")
    private String photoPath; // 频道图片

    @Column(name = "themeType")
    private String themeType; // 风格类型

    @Column(name = "themeConcreteType")
    private String themeConcreteType; // 风格类型明细（节日）

    @Column(name = "channelVersion")
    private String channelVersion; // 频道版本

    @Column(name = "musicStyle")
    private String musicStyle; // 音乐风格

    @Column(name = "site")
    private String site; // 场所

    @Column(name = "speed")
    private String speed; // 速度

    @Column(name = "voice")
    private String voice; // 人声(0男声，1女声，3合唱，4乐器)

    @Column(name = "element")
    private String element; // 元素

    @Column(name = "emotion")
    private String emotion; // 情绪

    @Column(name = "instrument")
    private String instrument; // 乐器

    @Column(name = "status")
    private String status; // 状态（暂时没用）

    @Column(name = "channelType")
    private String channelType; // 频道类型(0子频道,1组合频道,2插播频道)

    @Column(name = "favorite")
    private String favorite = Constants.FAVORITE_DEFAULT; // 收藏标志

    @Column(name = "profile")
    private String remarks; // 频道简介

    @ManyToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "cdboo_channel_music",
            inverseJoinColumns = {@JoinColumn(name = "music_id", referencedColumnName = "music_id")},
            joinColumns = {@JoinColumn(name = "channel_id", referencedColumnName = "channel_id")})
    private List<RestMusic> musicList = Lists.newArrayList(); //如果是子频道，该集合有对应music的信息

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "cdboo_channel_children",
            joinColumns = {@JoinColumn(name = "child_channel_id", referencedColumnName = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_channel_id", referencedColumnName = "channel_id")})
    private List<RestChannel> childChannelList = Lists.newArrayList(); //如果是组合频道，该集合有值

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

package com.cdboo.business.entity;

import com.cdboo.business.common.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * 用户音乐信息
 */
@Entity
@Table(name = "cdboo_music")
public class RestMusic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private long id;

    @Column(name = "music_NO")
    private String musicNo; // 音乐编号

    @Column(name = "music_owner")
    private String musicOwner; // 音乐拥有者类型

    @Column(name = "music_name")
    private String musicName; // 音乐名称

    @Column(name = "duration")
    private String duration; // 音乐时长

    @Column(name = "actor")
    private String actor; // 艺人

    @Column(name = "special")
    private String special; // 专辑

    @Column(name = "volume")
    private String volume; // 音量

    @Column(name = "status")
    private String status; // 状态（暂时没用）

    @Column(name = "path")
    private String path; // 音乐路径

    @Column(name = "favorite")
    private String favorite = Constants.FAVORITE_DEFAULT; // 收藏标志

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "cdboo_channel_music",
            joinColumns = {@JoinColumn(name = "music_id", referencedColumnName = "music_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id", referencedColumnName = "channel_id")})
    private List<RestChannel> channelList = Lists.newArrayList();

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

    public List<RestChannel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<RestChannel> channelList) {
        this.channelList = channelList;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

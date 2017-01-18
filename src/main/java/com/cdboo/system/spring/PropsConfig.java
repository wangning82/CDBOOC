package com.cdboo.system.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by houyi on 2017/1/16 0016.
 */
@ConfigurationProperties(prefix = "path.download", locations = "classpath:cdboo.properties")
public class PropsConfig {
    private String music;
    private String images;

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}

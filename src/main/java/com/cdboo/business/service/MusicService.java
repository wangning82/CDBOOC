package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.repository.ChannelRepository;
import com.cdboo.business.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ChannelRepository channelRepository;

    /**
     * 查询收藏音乐
     * @return
     */
    public List<RestMusic> findFavoriteList(){
        return musicRepository.findByFavorite(Constants.FAVORITE_YES);
    }

    /**
     * 查询歌单
     * @param channelId
     * @return
     */
    public List<RestMusic> findMusicByChannel(String channelId){
        List<RestMusic> result = new ArrayList();
        RestChannel restChannel = channelRepository.findOne(Long.parseLong(channelId));
        if(Constants.CHANNEL_TYPE_GROUP.equals(restChannel.getChannelType())){
            for(RestChannel channel : restChannel.getChildChannelList()){
                for(RestMusic music : channel.getMusicList()){
                    if(!result.contains(music)){
                        result.add(music);
                    }
                }
            }
        }else {
            result = restChannel.getMusicList();
        }
        return result;
    }


}

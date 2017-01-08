package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by houyi on 2016/12/9.
 */
@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public void save(RestChannel channel){
        channelRepository.save(channel);
    }

    public List<RestChannel> findAll(){
        return channelRepository.findAll();
    }

    public void deleteAll(){
        channelRepository.deleteAll();
    }

    public List<RestChannel> findFavoriteList(){
        return channelRepository.findByFavorite(Constants.FAVORITE_YES);
    }

}

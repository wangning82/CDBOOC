package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    /**
     * 查询收藏音乐
     * @return
     */
    public List<RestMusic> findFavoriteList(){
        return musicRepository.findByFavorite(Constants.FAVORITE_YES);
    }


}

package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.QPlanModel;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.repository.ChannelRepository;
import com.cdboo.business.repository.MusicRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private PlanService planService;

    private RestMusic findMusicById(Long musicId) {
        return musicRepository.findOne(musicId);
    }

    /**
     * 更新收藏状态
     *
     * @param musicId
     * @param favorite
     */
    public void updateFavorite(Long musicId, String favorite) {
        RestMusic music = findMusicById(musicId);
        music.setFavorite(favorite);
        musicRepository.flush();
    }

    /**
     * 查询收藏音乐
     *
     * @return
     */
    public List<RestMusic> findFavoriteList() {
        return musicRepository.findByFavorite(Constants.FAVORITE_YES);
    }

    /**
     * 查询频道歌单
     *
     * @param channelId
     * @return
     */
    public List<RestMusic> findMusicByChannel(Long channelId) {
        List<RestMusic> result = new ArrayList();
        RestChannel restChannel = channelRepository.findOne(channelId);
        if (Constants.CHANNEL_TYPE_GROUP.equals(restChannel.getChannelType())) {
            for (RestChannel channel : restChannel.getChildChannelList()) {
                for (RestMusic music : channel.getMusicList()) {
                    if (!result.contains(music)) {
                        result.add(music);
                    }
                }
            }
        } else {
            result = restChannel.getMusicList();
        }
        return result;
    }

    /**
     * 查询当前播放列表
     *
     * @return
     */
    public List<RestMusic> findMusic() {
        List<RestMusic> result = new ArrayList();
        // 根据优先级，节日 > 主题 > 风格
        findMusicByStyle(result, Constants.MUSIC_FESTIVAL);
        findMusicByStyle(result, Constants.MUSIC_THEME);
        findMusicByStyle(result, Constants.MUSIC_MANNER);
        return result;
    }

    /**
     * 查询播放列表
     *
     * @param result
     * @param style
     */
    private void findMusicByStyle(List<RestMusic> result, String style) {
        Iterable<PlanModel> festivalList = planService.findPlanByStyle(style);
        for (PlanModel planModel : festivalList) {
            for (RestMusic restMusic : planModel.getChannel().getMusicList()) {
                if (!result.contains(restMusic)) {
                    result.add(restMusic);
                }
            }
        }
    }

    /**
     * 查询插播列表
     *
     * @return
     */
    public List<RestMusic> findSpotMusic() {
        List<RestMusic> result = new ArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String currentTime = sdf.format(new Date());

        Predicate predicate = planService.getPredicateByStyle(Constants.MUSIC_SPOT)
                .and(QPlanModel.planModel.timestep.starttime.lt(currentTime));
        Iterable<PlanModel> list = planService.findAll(predicate);
        for (PlanModel planModel : list) {
            // TODO 判断插播的间隔时间和次数
        }

        return result;
    }

}

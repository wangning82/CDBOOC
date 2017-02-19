package com.cdboo.business.service;

import com.cdboo.business.common.Config;
import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.*;
import com.cdboo.business.repository.ChannelRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2016/12/9.
 */
@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private PlanService planService;

    /**
     * 保存
     *
     * @param channel
     */
    public RestChannel save(RestChannel channel) {
        return channelRepository.save(channel);
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        channelRepository.deleteAll();
    }

    /**
     * 查询频道
     *
     * @param channelId
     * @return
     */
    public RestChannel findChannelById(Long channelId) {
        return channelRepository.findOne(channelId);
    }

    /**
     * 查找音乐所属频道
     *
     * @param music
     * @return
     */
    public Iterable<RestChannel> findChannelByMusic(RestMusic music) {
        Predicate predicate = QRestChannel.restChannel.musicList.contains(music);
        return channelRepository.findAll(predicate);
    }

    /**
     * 更新收藏状态
     *
     * @param channelId
     * @param favorite
     */
    public void updateFavorite(Long channelId, String favorite) {
        RestChannel channel = findChannelById(channelId);
        channel.setFavorite(favorite);
        channelRepository.flush();
    }

    /**
     * 查询收藏的频道
     *
     * @return
     */
    public List<RestChannel> findFavoriteList() {
        return channelRepository.findByFavorite(Constants.FAVORITE_YES);
    }

    /**
     * 查询频道列表
     *
     * @param style 风格
     * @param scene 场景业态
     * @return
     */
    public List<RestChannel> findChannelList(String style, String scene) {
        List<RestChannel> result = new ArrayList<RestChannel>();
        BooleanExpression predicate = null;
        if (scene != null) {
            predicate = planService.getPredicateByStyle(style)
                    .and(planService.getPredicateByScene(scene));
            if (Constants.MUSIC_THEME.equals(style) || Constants.MUSIC_MANNER.equals(style)) {
                predicate = predicate.and(planService.getPredicateByWeek());
            } else if (Constants.MUSIC_SPOT.equals(style) || Constants.MUSIC_FESTIVAL.equals(style)) {
                predicate = predicate.and(planService.getPredicateByDate()).and(planService.getPredicateByTime());
            }
        } else {
            predicate = QPlanModel.planModel.musicStyle.eq(style);
        }
        for(String periodName : Config.getConfigInstance().getPeriodList()){
            predicate = predicate.and(planService.getPredictateByPeriodName(periodName));
        }
        Iterable<PlanModel> list = planService.findAll(predicate);
        for (PlanModel planModel : list) {
            if (!result.contains(planModel.getChannel())) {
                result.add(planModel.getChannel());
            }
        }
        return result;
    }

}

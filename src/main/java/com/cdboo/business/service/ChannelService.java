package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.QPlanModel;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.repository.ChannelRepository;
import com.cdboo.business.repository.PlanRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public void save(RestChannel channel) {
        channelRepository.save(channel);
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        channelRepository.deleteAll();
    }

    private RestChannel findChannelById(Long channelId) {
        return channelRepository.findOne(channelId);
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
        Predicate predicate = null;
        if (scene != null) {
            predicate = planService.getPredicateByStyleAndTime(style)
                    .and(QPlanModel.planModel.scene.eq(scene));
        } else {
            predicate = QPlanModel.planModel.musicStyle.eq(style);
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

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
    private PlanRepository planRepository;

    public void save(RestChannel channel){
        channelRepository.save(channel);
    }

    public void deleteAll(){
        channelRepository.deleteAll();
    }

    /**
     * 查询收藏的频道
     * @return
     */
    public List<RestChannel> findFavoriteList(){
        return channelRepository.findByFavorite(Constants.FAVORITE_YES);
    }

    /**
     * 查询频道列表
     * @param style 风格
     * @param scene 场景业态
     * @return
     */
    public List<RestChannel> findChannelList(String style, String scene){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        List<RestChannel> result = new ArrayList<RestChannel>();
        Predicate predicate = null;
        if(scene != null){
            predicate = QPlanModel.planModel.musicStyle.eq(style)
                    .and(QPlanModel.planModel.scene.eq(scene))
                    .and(QPlanModel.planModel.timestep.starttime.lt(currentTime))
                    .and(QPlanModel.planModel.timestep.endtime.gt(currentTime));
        }else{
            predicate = QPlanModel.planModel.musicStyle.eq(style);
        }
        Iterable<PlanModel> list = planRepository.findAll(predicate);
        for(PlanModel planModel : list){
            if(!result.contains(planModel.getChannel())){
                result.add(planModel.getChannel());
            }
        }
        return result;
    }

}

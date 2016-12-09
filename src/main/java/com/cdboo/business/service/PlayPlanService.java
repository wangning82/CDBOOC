package com.cdboo.business.service;

import com.cdboo.business.entity.PlayPlan;
import com.cdboo.business.repository.PlayPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by houyi on 2016/12/9.
 */
@Service
public class PlayPlanService {

    @Autowired
    private PlayPlanRepository playPlanRepository;

    public void save(PlayPlan playPlan){
        playPlanRepository.save(playPlan);
    }

    public List<PlayPlan> findAll(){
        return playPlanRepository.findAll();
    }

    /**
     * 获取当前播放计划
     * @return
     */
    public PlayPlan getCurrentPlayPlan(){
        // mokito, TODO
        PlayPlan plan = new PlayPlan();
        plan.setName("晨醒");
        return plan;
    }

}

package com.cdboo.business.service;

import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.QPlanModel;
import com.cdboo.business.repository.PlanRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public void deleteAll(){
        planRepository.deleteAll();
    }

    public void save(PlanModel planModel){
        planRepository.save(planModel);
    }

    /**
     * 查询场景业态
     * @return
     */
    public List<String> findSceneList(){
        List<String> result = new ArrayList<String>();
        List<PlanModel> list = planRepository.findAll();
        for(PlanModel planModel : list){
            if(!result.contains(planModel.getScene())){
                result.add(planModel.getScene());
            }
        }
        return result;
    }

    /**
     * 条件查询
     * @param musicStyle
     * @return
     */
    public BooleanExpression getPredicateByStyle(String musicStyle){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        return QPlanModel.planModel.musicStyle.eq(musicStyle)
                .and(QPlanModel.planModel.week.contains(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK))))
                .and(QPlanModel.planModel.startDate.lt(currentDate))
                .and(QPlanModel.planModel.startDate.gt(currentDate));
    }

    /**
     * 条件查询
     * @param musicStyle
     * @return
     */
    public BooleanExpression getPredicateByStyleAndTime(String musicStyle){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String currentTime = sdf.format(new Date());

        return getPredicateByStyle(musicStyle)
                .and(QPlanModel.planModel.timestep.starttime.lt(currentTime))
                .and(QPlanModel.planModel.timestep.endtime.gt(currentTime));
    }

    /**
     * 查询播放计划
     * @param predicate
     * @return
     */
    public Iterable<PlanModel> findAll(Predicate predicate){
        return planRepository.findAll(predicate);
    }


}

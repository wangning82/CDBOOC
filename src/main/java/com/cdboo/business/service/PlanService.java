package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
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

    public void deleteAll() {
        planRepository.deleteAll();
    }

    public PlanModel save(PlanModel planModel) {
        return planRepository.save(planModel);
    }

    /**
     * 查询场景业态
     *
     * @return
     */
    public List<String> findSceneList() {
        List<String> result = new ArrayList<String>();
        List<PlanModel> list = planRepository.findAll();
        for (PlanModel planModel : list) {
            if (!result.contains(planModel.getScene())) {
                result.add(planModel.getScene());
            }
        }
        return result;
    }

    /**
     * 日期条件查询
     *
     * @return
     */
    public BooleanExpression getPredicateByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        return QPlanModel.planModel.startDate.lt(currentDate)
                .and(QPlanModel.planModel.endDate.gt(currentDate));
    }

    /**
     * 风格条件查询
     *
     * @param musicStyle
     * @return
     */
    public BooleanExpression getPredicateByStyle(String musicStyle) {
        return QPlanModel.planModel.musicStyle.eq(musicStyle);
    }

    /**
     * 节日查询
     * @param themeConcreteType
     * @return
     */
    public BooleanExpression getPredicateBythemeConcrete(String themeConcreteType) {
        return QPlanModel.planModel.channel.themeConcreteType.eq(themeConcreteType);
    }

    /**
     * 业态条件查询
     *
     * @param scene
     * @return
     */
    public BooleanExpression getPredicateByScene(String scene) {
        return QPlanModel.planModel.scene.eq(scene);
    }

    /**
     * 星期条件查询
     *
     * @return
     */
    public BooleanExpression getPredicateByWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return QPlanModel.planModel.week.contains(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));
    }

    /**
     * 时段条件查询
     *
     * @return
     */
    public BooleanExpression getPredicateByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String currentTime = sdf.format(new Date());
        return QPlanModel.planModel.timestep.starttime.lt(currentTime)
                .and(QPlanModel.planModel.timestep.endtime.gt(currentTime));
    }

    /**
     * 查询播放计划
     *
     * @param predicate
     * @return
     */
    public Iterable<PlanModel> findAll(Predicate predicate) {
        return planRepository.findAll(predicate);
    }

    /**
     * 查询播放计划
     *
     * @param musicStyle
     * @return
     */
    public Iterable<PlanModel> findPlanByStyle(String musicStyle) {
        BooleanExpression predicate = getPredicateByStyle(musicStyle).and(getPredicateByTime()).and(getPredicateByWeek());
        if(Constants.MUSIC_FESTIVAL.equals(musicStyle)){
            predicate = predicate.and(getPredicateByDate());
        }
        return findAll(predicate);
    }

    /**
     * 查询节日计划
     * @param festival
     * @return
     */
    public Iterable<PlanModel> findFestivalPlan(String festival){
        BooleanExpression predicate = getPredicateByStyle(Constants.MUSIC_FESTIVAL).and(getPredicateBythemeConcrete(festival));
        return findAll(predicate);
    }

    /**
     * 查询节日
     * @return
     */
    public List<String> findFestivalList(){
        List<String> result = new ArrayList<>();
        Iterable<PlanModel> list = findAll(getPredicateByStyle(Constants.MUSIC_FESTIVAL));
        for(PlanModel planModel : list){
            if(!result.contains(planModel.getChannel().getThemeConcreteType())){
                result.add(planModel.getChannel().getThemeConcreteType());
            }
        }
        return result;
    }

    /**
     * 查询插播计划
     *
     * @return
     */
    public Iterable<PlanModel> findSpotPlan() {
        BooleanExpression predicate = getPredicateByStyle(Constants.MUSIC_SPOT).and(getPredicateByDate());
        return findAll(predicate);
    }

}

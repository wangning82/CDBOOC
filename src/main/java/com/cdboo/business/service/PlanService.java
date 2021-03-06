package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.QPlanModel;
import com.cdboo.business.model.SceneModel;
import com.cdboo.business.repository.PlanRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public PlanModel findById(Long id){
        return planRepository.findOne(id);
    }

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
    public List<SceneModel> findSceneList() {
        List<SceneModel> result = new ArrayList<SceneModel>();
        List<PlanModel> list = planRepository.findAll();
        for (PlanModel planModel : list) {
            SceneModel scene = new SceneModel();
            scene.setIcon(planModel.getSceneImg());
            scene.setName(planModel.getScene());
            if (!StringUtils.isEmpty(planModel.getScene()) && !result.contains(scene)) {
                result.add(scene);
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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        return QPlanModel.planModel.timestep.starttime.lt(currentTime)
                .and(QPlanModel.planModel.timestep.endtime.gt(currentTime));
    }

    /**
     * 时段名字查询
     *
     * @param periodName
     * @return
     */
    public BooleanExpression getPredictateByPeriodName(String periodName){
        return QPlanModel.planModel.timestep.timestepName.eq(periodName);
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
     * 查询当前时段播放计划
     *
     * @param musicStyle
     * @return
     */
    public Iterable<PlanModel> findPlanByStyle(String musicStyle) {
        BooleanExpression predicate = getPredicateByStyle(musicStyle).and(getPredicateByTime()).and(getPredicateByDate());
        if(!Constants.MUSIC_FESTIVAL.equals(musicStyle)){
            predicate = predicate.and(getPredicateByWeek());
        }
        return findAll(predicate);
    }

    /**
     * 查询所有播放计划
     * @param musicStyle
     * @return
     */
    public Iterable<PlanModel> findAllPlanByStyle(String musicStyle) {
        return findAll(getPredicateByStyle(musicStyle));
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
        BooleanExpression predicate = getPredicateByStyle(Constants.MUSIC_SPOT);
        return findAll(predicate);
    }

}

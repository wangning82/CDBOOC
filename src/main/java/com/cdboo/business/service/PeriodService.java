package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.QPlanModel;
import com.cdboo.business.entity.RestTimeStep;
import com.cdboo.business.repository.PeriodRepository;
import com.cdboo.business.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PlanRepository planRepository;

    public RestTimeStep save(RestTimeStep period){
        return periodRepository.save(period);
    }

    public List<RestTimeStep> findAll(){
        Iterable<PlanModel> list = planRepository.findAll(QPlanModel.planModel.musicStyle.ne(Constants.MUSIC_SPOT));
        List<RestTimeStep> result = new ArrayList<>();
        for(PlanModel plan : list){
            if(!result.contains(plan.getTimestep())){
                result.add(plan.getTimestep());
            }
        }
        return result;
    }

    public void deleteAll() {
        periodRepository.deleteAll();
    }
}

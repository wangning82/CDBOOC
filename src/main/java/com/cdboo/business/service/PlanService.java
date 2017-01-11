package com.cdboo.business.service;

import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<PlanModel> findPlanList(String style){
        return null;
    }


}

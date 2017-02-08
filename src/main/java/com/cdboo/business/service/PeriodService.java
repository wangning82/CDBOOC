package com.cdboo.business.service;

import com.cdboo.business.entity.RestTimeStep;
import com.cdboo.business.repository.PeriodRepository;
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

    public RestTimeStep save(RestTimeStep period){
        return periodRepository.save(period);
    }

    public List<RestTimeStep> findAll(){
        List<RestTimeStep> result = new ArrayList<>();
        for(RestTimeStep obj : periodRepository.findAll()){
            if(!result.contains(obj)){
                result.add(obj);
            }
        }
        return result;
    }

    public void deleteAll() {
        periodRepository.deleteAll();
    }
}

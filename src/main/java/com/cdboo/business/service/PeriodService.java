package com.cdboo.business.service;

import com.cdboo.business.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;
}

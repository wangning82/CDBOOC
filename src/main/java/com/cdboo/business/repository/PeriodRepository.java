package com.cdboo.business.repository;

import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.entity.RestTimeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Repository
public interface PeriodRepository extends JpaRepository<RestTimeStep, Long>, QueryDslPredicateExecutor<RestTimeStep> {


}

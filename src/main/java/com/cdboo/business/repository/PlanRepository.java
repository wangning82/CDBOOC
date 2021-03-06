package com.cdboo.business.repository;

import com.cdboo.business.entity.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by houyi on 2016/12/30.
 */
@Repository
public interface PlanRepository extends JpaRepository<PlanModel, Long>, QueryDslPredicateExecutor<PlanModel> {


}

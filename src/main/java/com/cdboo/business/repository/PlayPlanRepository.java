package com.cdboo.business.repository;

import com.cdboo.business.entity.PlayPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by houyi on 2016/12/9.
 */
@Repository
public interface PlayPlanRepository extends JpaRepository<PlayPlan, Long> {
}
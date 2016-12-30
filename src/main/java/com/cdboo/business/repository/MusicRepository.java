package com.cdboo.business.repository;

import com.cdboo.business.entity.RestMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Repository
public interface MusicRepository extends JpaRepository<RestMusic, Long> {
}

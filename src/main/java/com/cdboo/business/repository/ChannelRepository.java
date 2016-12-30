package com.cdboo.business.repository;

import com.cdboo.business.entity.RestChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by houyi on 2016/12/9.
 */
@Repository
public interface ChannelRepository extends JpaRepository<RestChannel, Long> {
}

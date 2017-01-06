package com.cdboo.business.repository;

import com.cdboo.business.entity.RestChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by houyi on 2016/12/9.
 */
@Repository
public interface ChannelRepository extends JpaRepository<RestChannel, Long>, QueryDslPredicateExecutor<RestChannel> {

    /**
     * 查找已收藏频道
     * @param favorite
     * @return
     */
    @Query("SELECT t FROM RestChannel t WHERE t.favorite = :favorite")
    List<RestChannel> findByFavorite(@Param("favorite") String favorite);

}

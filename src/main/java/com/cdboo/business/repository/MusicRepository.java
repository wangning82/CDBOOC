package com.cdboo.business.repository;

import com.cdboo.business.entity.RestMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Repository
public interface MusicRepository extends JpaRepository<RestMusic, Long>, QueryDslPredicateExecutor<RestMusic> {

    /**
     * 查找已收藏歌曲
     * @param favorite
     * @return
     */
    @Query("SELECT t FROM RestMusic t WHERE t.favorite = :favorite")
    List<RestMusic> findByFavorite(@Param("favorite") String favorite);

}

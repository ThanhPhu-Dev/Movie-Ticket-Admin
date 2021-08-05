package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query(value = "select movies.* from movies join category_movie cm on movies.id = cm.movie_id" +
                   " join categories cate on cm.category_id = cate.id" +
                   " where cate.id = ?2 and UPPER(movies.\"name\") like UPPER(concat('%',?1,'%'))", nativeQuery = true)
    List<MovieEntity> findByNameContainingAndNameCategoryCustom(String name, Integer idCategory);

    @Query("select a from MovieEntity a where upper(a.name) like upper(concat('%', ?1,'%'))")
    List<MovieEntity> findByNameContainingCustom(String name);

    @Query(value = "select movies.* from movies join category_movie cm on movies.id = cm.movie_id" +
                   " join categories cate on cm.category_id = cate.id" +
                   " where cate.id = ?2 and UPPER(movies.\"name\") like UPPER(concat('%',?1,'%'))",
          nativeQuery = true)
    Page<MovieEntity> findByNameContainingAndNameCategoryCustom(String name, Integer idCategory, Pageable pageable);

    @Query("select a from MovieEntity a where upper(a.name) like upper(concat('%', ?1,'%'))")
    Page<MovieEntity> findByNameContainingCustom(String name, Pageable pageable);
}
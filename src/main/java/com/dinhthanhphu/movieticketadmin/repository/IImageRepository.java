package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IImageRepository extends JpaRepository<ImageEntity, Long> {

    @Query(value = "select * from images where movie_id = ?1", nativeQuery = true)
    List<ImageEntity> findAllByMovie_id(Integer movie_id);
}
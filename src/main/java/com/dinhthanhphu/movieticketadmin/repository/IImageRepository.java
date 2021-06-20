package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<ImageEntity, Long> {
}
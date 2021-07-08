package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findOneByEmail(String email);
    UserEntity findByEmailAndProvider(String email, String provider);
}
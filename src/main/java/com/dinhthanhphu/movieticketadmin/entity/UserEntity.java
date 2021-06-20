package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "hasedPassword")
    private String hasedPassword;

    @Column(name = "fullName")
    private String fullname;

    @Column(name = "phoneName")
    private String phoneName;

    @Column(name = "active")
    private boolean active;



}

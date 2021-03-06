package com.dinhthanhphu.movieticketadmin.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.security.Provider;
import java.util.UUID;

@Setter
@Getter
@Proxy(lazy = false)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "active")
    private boolean active;

    @Column(name = "code")
    private String code;

    @Column(name = "auth_provider")
    private String provider;
}

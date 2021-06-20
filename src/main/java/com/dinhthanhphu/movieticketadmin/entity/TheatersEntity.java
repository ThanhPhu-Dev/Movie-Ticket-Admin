package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "theaters")
public class TheatersEntity extends BaseEntity {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "theater")
    private List<CinemaEntity> cinema = new ArrayList<>();
}
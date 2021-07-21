package com.dinhthanhphu.movieticketadmin.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Actor")
@Data
public class ActorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String public_url;

    @Column
    private String public_id;

    @Column(name = "biography", length = 4000)
    private String biography;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;
}
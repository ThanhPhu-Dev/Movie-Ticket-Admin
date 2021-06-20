package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "showtimes")
@Entity
@Data
public class ShowtimeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "fare")
    private Float fare;

    @ManyToOne()
    @JoinColumn(name = "cinema_id")
    private MovieEntity cinema;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
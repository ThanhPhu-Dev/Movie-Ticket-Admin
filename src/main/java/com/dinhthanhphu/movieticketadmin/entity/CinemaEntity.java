package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "cinemas")
@Entity
@Data
public class CinemaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer lenght;

    @Column
    private Integer width;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private TheatersEntity theater;

    @OneToMany(mappedBy = "cinema")
    private List<ShowtimeEntity> showtime;

}
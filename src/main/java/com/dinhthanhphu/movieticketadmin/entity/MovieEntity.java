package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Movies")
@Entity
@Data
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @Column
    private Integer times;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column
    private Date openDate;

    @Column
    private String posterUrl;

    @Column
    private String posterPublicId;

    @Column
    private String trailerUrl;

    @Column
    private String nation;

    @Column
    private String trailerPublicid;

    @OneToMany(mappedBy = "movie")
    private List<ShowtimeEntity> showtime;

    @OneToMany(mappedBy = "movie")
    private List<ImageEntity> image;

    @ManyToMany(mappedBy = "movies")
    private List<ActorEntity> actors;

    @ManyToMany(mappedBy = "movies")
    private List<CategoryEntity> categories;
}
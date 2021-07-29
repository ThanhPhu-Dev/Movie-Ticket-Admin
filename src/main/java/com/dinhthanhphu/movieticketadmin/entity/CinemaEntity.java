package com.dinhthanhphu.movieticketadmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "cinemas")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private Integer lenght;

    @Column
    private Integer width;


    @OneToMany(mappedBy = "cinema")
    private List<ShowtimeEntity> showtime;

}
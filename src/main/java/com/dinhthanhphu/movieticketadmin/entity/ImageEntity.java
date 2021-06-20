package com.dinhthanhphu.movieticketadmin.entity;

import javax.persistence.*;

@Table(name = "Images")
@Entity
public class ImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String publicId;

    @Column
    private  String publicUrl;

    @ManyToOne
    private MovieEntity movie;
}
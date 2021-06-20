package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Images")
@Entity
@Data
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
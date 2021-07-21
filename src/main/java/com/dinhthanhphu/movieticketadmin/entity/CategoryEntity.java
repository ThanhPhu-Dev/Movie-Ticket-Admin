package com.dinhthanhphu.movieticketadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "categories")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<MovieEntity> movies;
}
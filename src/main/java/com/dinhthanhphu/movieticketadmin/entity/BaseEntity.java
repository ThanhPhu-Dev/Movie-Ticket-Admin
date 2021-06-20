package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "modifyBy")
    private String modifyBy;

    @Column(name = "modifyDate")
    private Date modifyDate;
}

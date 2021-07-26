package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "createBy")
    @CreatedBy
    private String createBy;

    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "modifyBy")
    @LastModifiedDate
    private String modifyBy;

    @Column(name = "modifyDate")
    @LastModifiedBy
    private Date modifyDate;
}

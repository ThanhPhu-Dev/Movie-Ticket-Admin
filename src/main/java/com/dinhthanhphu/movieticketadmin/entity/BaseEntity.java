package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "createBy")
    @CreatedBy
    private String createBy;

    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "modifyBy")
    @LastModifiedBy
    private String modifyBy;

    @Column(name = "modifyDate")
    @LastModifiedDate
    private Date modifyDate;
}

package com.dinhthanhphu.movieticketadmin.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.util.Date;

@Data
public abstract class BaseDTO {

    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
}

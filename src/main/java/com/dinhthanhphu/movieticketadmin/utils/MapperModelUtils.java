package com.dinhthanhphu.movieticketadmin.utils;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MapperModelUtils {

    public <T1, T2> T1 convertToDTO(T1 dto, T2 entity) {

        try{
            BeanUtils.copyProperties(entity,dto);
        }catch (Exception e){
            return null;
        }
        return dto;
    }

    public <T1, T2> T2 convertToEntity(T1 dto, T2 entity){
        try {
            BeanUtils.copyProperties(dto,entity);
        }catch (Exception e){
            return null;
        }
        return entity;
    }
}

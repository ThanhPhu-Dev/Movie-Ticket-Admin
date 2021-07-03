package com.dinhthanhphu.movieticketadmin.convert;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
    public UserDTO convertToDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        try{
            BeanUtils.copyProperties(entity,dto);
        }catch (Exception e){
            return null;
        }
        return dto;
    }

    public UserEntity convertToEntity(UserDTO userDTO, UserEntity userEntity){
        try {
            BeanUtils.copyProperties(userDTO,userEntity);
        }catch (Exception e){
            return null;
        }
        return userEntity;
    }
}

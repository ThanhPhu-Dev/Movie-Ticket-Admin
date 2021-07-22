package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;

import java.util.List;

public interface IImageService {

    List<ImageDTO> findAllByMovie_id(String id);
}

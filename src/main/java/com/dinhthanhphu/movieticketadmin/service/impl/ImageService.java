package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.repository.IImageRepository;
import com.dinhthanhphu.movieticketadmin.service.IImageService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    public List<ImageDTO> findAllByMovie_id(String id) {
        return imageRepository.findAllByMovie_id(Integer.parseInt(id)).stream()
                .map(m -> cvt.convertToDTO(new ImageDTO(), m)).collect(Collectors.toList());
    }
}

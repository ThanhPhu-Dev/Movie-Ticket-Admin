package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import com.dinhthanhphu.movieticketadmin.repository.IImageRepository;
import com.dinhthanhphu.movieticketadmin.service.IImageService;
import com.dinhthanhphu.movieticketadmin.utils.CloudinaryUtils;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Autowired
    private CloudinaryUtils cloudUtil;

    @Override
    public List<ImageDTO> findAllByMovie_id(String id) {
        return imageRepository.findAllByMovie_id(Integer.parseInt(id)).stream()
                .map(m -> cvt.convertToDTO(new ImageDTO(), m)).collect(Collectors.toList());
    }

    @Async("asyncExecutor")
    @Override
    public ImageEntity save(MovieEntity movie, byte[] imageFile) {
        ImageEntity image = null;
        Map uploadResultImage = null;
        image = new ImageEntity();
        uploadResultImage = cloudUtil.uploadCloudinary(imageFile, "movieCinema/ImageDescription");
        if (uploadResultImage != null) {
            image.setPublicId(uploadResultImage.get("public_id").toString());
            image.setPublicUrl(uploadResultImage.get("url").toString());
            image.setMovie(movie);
            imageRepository.save(image);
        }
        return image;
    }
}

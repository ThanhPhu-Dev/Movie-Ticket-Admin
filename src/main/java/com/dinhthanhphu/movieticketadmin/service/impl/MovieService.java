package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;
import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.entity.CategoryEntity;
import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import com.dinhthanhphu.movieticketadmin.payload.MovieRequest;
import com.dinhthanhphu.movieticketadmin.repository.IActorRepository;
import com.dinhthanhphu.movieticketadmin.repository.ICategoryRepository;
import com.dinhthanhphu.movieticketadmin.repository.IImageRepository;
import com.dinhthanhphu.movieticketadmin.repository.IMovieRepository;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import com.dinhthanhphu.movieticketadmin.utils.CloudinaryUtils;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private CloudinaryUtils cloudUtil;

    @Autowired
    private IActorRepository actorRepository;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    @Transactional
    public MovieDTO save(MovieRequest form) {
        try {
            MovieEntity movie = null;
            if (form.getId() != null) {
                movie = movieRepository.findById(Long.parseLong(form.getId())).get();
            } else {
                movie = new MovieEntity();
            }
            List<ImageEntity> lstImage = new ArrayList<>();
            List<ActorEntity> lstActor = new ArrayList<>();
            List<CategoryEntity> lstCategory = new ArrayList<>();
            ImageEntity image = null;
            ActorEntity actor = null;
            CategoryEntity category = null;
            Map uploadResult = null;
            if (form.getAvatar().getSize() > 0) {
                uploadResult = cloudUtil.uploadCloudinary(form.getAvatar(), "movieCinema/PosterMovie");
                movie.setPosterPublicId(uploadResult.get("public_id").toString());
                movie.setPosterUrl(uploadResult.get("url").toString());
            }
            if (form.getVideo().getSize() > 0) {
                uploadResult = cloudUtil.uploadCloudinary(form.getVideo(), "movieCinema/VideoMovie");
                movie.setTrailerPublicid(uploadResult.get("public_id").toString());
                movie.setTrailerUrl(uploadResult.get("url").toString());
            }

            for (String nameActor : form.getActor()) {
                if (!nameActor.equals("")) {
                    actor = actorRepository.findByNameContaining(nameActor);
                    if (actor != null) {
//                    actor.getMovies().add(movie);
                        lstActor.add(actor);
                    }
                }
            }
            for (String nameCategory : form.getCategory()) {
                if (!nameCategory.equals("")) {
                    category = categoryRepository.findByNameContaining(nameCategory);
                    if (category != null) {
                        lstCategory.add(category);
                    }
                }
            }
            movie.setName(form.getName());
            movie.setDescription(form.getDescription());
            movie.setNation(form.getNation());
            movie.setTimes(Integer.parseInt(form.getTime()));
            movie.setOpenDate(new SimpleDateFormat("yyyy-MM-dd").parse(form.getOpenDate()));

            movie.setActors(lstActor);
            movie.setCategories(lstCategory);

            movieRepository.save(movie);

            for (MultipartFile i : form.getImageDescription()) {
                if (i.getSize() > 0) {
                    image = new ImageEntity();
                    uploadResult = cloudUtil.uploadCloudinary(i, "movieCinema/ImageDescription");
                    image.setPublicId(uploadResult.get("public_id").toString());
                    image.setPublicUrl(uploadResult.get("url").toString());
                    image.setMovie(movie);
                    imageRepository.save(image);
                    lstImage.add(image);
                }
            }
            if(form.getImageDelete() != null){
                for (String imagedelete : form.getImageDelete()){
                    image = imageRepository.findByPublicId(imagedelete);
                    imageRepository.delete(image);
                    cloudUtil.delete(imagedelete);
                }
            }
            return cvt.convertToDTO(MovieDTO.builder()
                    .image(lstImage.stream().map(m -> cvt.convertToDTO(new ImageDTO(), m)).collect(Collectors.toList()))
                    .actors(lstActor.stream().map(m -> cvt.convertToDTO(new ActorDTO(), m)).collect(Collectors.toList()))
                    .categories(lstCategory.stream().map(m -> cvt.convertToDTO(new CategoryDTO(), m)).collect(Collectors.toList()))
                    .build(), movie);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(m -> cvt.convertToDTO(new MovieDTO(), m)).collect(Collectors.toList());
    }

    @Override
    public MovieDTO findOneById(String id) {
        MovieEntity movie = movieRepository.findById(Long.parseLong(id)).get();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setCategories(movie.getCategories().stream().map(m -> cvt.convertToDTO(new CategoryDTO(), m)).collect(Collectors.toList()));
        movieDTO.setActors(movie.getActors().stream().map(m -> cvt.convertToDTO(new ActorDTO(), m)).collect(Collectors.toList()));
        movieDTO.setImage(movie.getImage().stream().map(m -> cvt.convertToDTO(new ImageDTO(), m)).collect(Collectors.toList()));
        return cvt.convertToDTO(movieDTO, movie);
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        try {
            MovieEntity movie = movieRepository.findById(Long.parseLong(id)).get();
            if (movie != null) {

                movieRepository.delete(movie);
                return true;
            } else {
                return false;
            }
        } catch (Exception error) {
            return false;
        }
    }


}

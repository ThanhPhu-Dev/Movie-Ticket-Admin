package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;
import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.entity.CategoryEntity;
import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import com.dinhthanhphu.movieticketadmin.payload.request.MovieRequest;
import com.dinhthanhphu.movieticketadmin.repository.IActorRepository;
import com.dinhthanhphu.movieticketadmin.repository.ICategoryRepository;
import com.dinhthanhphu.movieticketadmin.repository.IImageRepository;
import com.dinhthanhphu.movieticketadmin.repository.IMovieRepository;
import com.dinhthanhphu.movieticketadmin.service.IImageService;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import com.dinhthanhphu.movieticketadmin.utils.CloudinaryUtils;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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

    @Autowired
    private IImageService imageService;

    @Override
    @Transactional
    public MovieDTO save(MovieRequest form) {
        try {
            List<ActorEntity> lstActor = new ArrayList<>();
            List<CategoryEntity> lstCategory = new ArrayList<>();
            ImageEntity image = null;
            ActorEntity actor = null;
            CategoryEntity category = null;
            CompletableFuture<Map> uploadResultPoster = null;
            CompletableFuture<Map> uploadResultVideo = null;
            MovieEntity movie = null;
            Long t1 = System.currentTimeMillis();
            System.out.println("start: "+ t1);
            if (form.getId() != null) {
                movie = movieRepository.findById(Long.parseLong(form.getId())).get();
                System.out.println("imgae: "+movie.getPosterPublicId() +": "+form.getAvatar().getBytes());
                uploadResultPoster = cloudUtil.uploadPublicIdAsync(form.getAvatar(), movie.getPosterPublicId());
                uploadResultVideo = cloudUtil.uploadPublicIdAsync(form.getVideo(), movie.getTrailerPublicid());
            } else {
                movie = new MovieEntity();
                uploadResultPoster = cloudUtil.uploadCloudinaryAsync(form.getAvatar(), "movieCinema/PosterMovie");
                uploadResultVideo = cloudUtil.uploadCloudinaryAsync(form.getVideo(), "movieCinema/VideoMovie");
            }
            Long t2 = System.currentTimeMillis();
            System.out.println("start thread: "+ t2);
            CompletableFuture.allOf(uploadResultPoster, uploadResultVideo).join();
            Long t3 = System.currentTimeMillis();
            System.out.println("start setter: "+ t3);
            if (uploadResultPoster.get() != null) {
                System.out.println("sau khi setPosterPublicId: "+ uploadResultPoster.get().get("public_id").toString());
                System.out.println("sau khi setPosterUrl: "+ uploadResultPoster.get().get("url").toString());
                movie.setPosterPublicId(uploadResultPoster.get().get("public_id").toString());
                movie.setPosterUrl(uploadResultPoster.get().get("url").toString());
            }

            if (uploadResultVideo.get() != null) {
                System.out.println("sau khi setTrailerPublicid: "+ uploadResultVideo.get().get("public_id").toString());
                System.out.println("sau khi setTrailerUrl: "+ uploadResultVideo.get().get("url").toString());
                movie.setTrailerPublicid(uploadResultVideo.get().get("public_id").toString());
                movie.setTrailerUrl(uploadResultVideo.get().get("url").toString());
            }
            Long t4 = System.currentTimeMillis();
            System.out.println("start db: "+ t4);
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
            Long t5 = System.currentTimeMillis();
            System.out.println("end db: "+ t5);
            MovieEntity finalMovie = movie;
            for (MultipartFile i : form.getImageDescription()) {
                CompletableFuture.runAsync(() -> {
                    try {
                        imageService.save(finalMovie, i.getBytes());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            }

            if (form.getImageDelete() != null) {
                for (String imagedelete : form.getImageDelete()) {
                    image = imageRepository.findByPublicId(imagedelete);
                    imageRepository.delete(image);
                    cloudUtil.delete(imagedelete);
                }
            }
            Long t6 = System.currentTimeMillis();
            System.out.println("end: "+ t6);
            System.out.println("Tong: "+ (t6-t1) + "Video + poster: " + (t2-t1) + "Set posetr and video" + (t4-t3) +"Luu db: " + (t5-t4));
            return cvt.convertToDTO(MovieDTO.builder()
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

    @Override
    public List<MovieDTO> findByNameAndIdCategory(String name, String idCategory) {
        List<MovieEntity> lstMovie = null;
        String namemovie = "";
        if (name != null) {
            namemovie = name;
        }
        if (Integer.parseInt(idCategory) == 0) {
            lstMovie = movieRepository.findByNameContainingCustom(namemovie);
        } else {
            lstMovie = movieRepository.findByNameContainingAndNameCategoryCustom(namemovie, Integer.parseInt(idCategory));
        }
        return lstMovie.stream().map(m -> cvt.convertToDTO(new MovieDTO(), m)).collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findByIdCategory(String idCategory) {
        CategoryEntity category = categoryRepository.findById(Long.parseLong(idCategory)).orElse(null);
        List<MovieEntity> result = null;
        if (category == null) {
            result = movieRepository.findAll();
        } else {
            result = category.getMovies();
        }
        return result.stream()
                .map(m -> cvt.convertToDTO(new MovieDTO(), m))
                .collect(Collectors.toList());
    }

    @Override
    public Page<MovieDTO> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("createDate").descending());
        return movieRepository.findAll(pageable).map(m -> cvt.convertToDTO(new MovieDTO(), m));
    }

    @Override
    public Page<MovieDTO> findByNameAndIdCategoryPaginated(int pageNo, int pageSize, String name, String idCategory) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("createDate").descending());
        Page<MovieEntity> page = null;
        String namemovie = "";
        if (name != null) {
            namemovie = name;
        }
        if (Integer.parseInt(idCategory) == 0) {
            page = movieRepository.findByNameContainingCustom(namemovie, pageable);
        } else {
            page = movieRepository.findByNameContainingAndNameCategoryCustom(namemovie, Integer.parseInt(idCategory), pageable);
        }
        return page.map(m -> cvt.convertToDTO(new MovieDTO(), m));
    }

    @Override
    public Page<MovieDTO> findByIdCategoryPaginated(String idCategory, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("createDate").descending());
        Page<MovieEntity> page = null;

        if (idCategory.equals("0")) {
            page = movieRepository.findAll(pageable);
        } else {
            CategoryEntity category = categoryRepository.findById(Long.parseLong(idCategory)).orElse(null);
            List<MovieEntity> rs = category.getMovies();
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), rs.size());
            page = new PageImpl<>(rs.subList(start, end), pageable, rs.size());
        }
        return page.map(m -> cvt.convertToDTO(new MovieDTO(), m));
    }
}

package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.CategoryEntity;
import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO extends BaseDTO{

    private Long Id;
    private String name;
    private Integer times;
    private String description;
    private Date openDate;
    private String nation;
    private String posterUrl;
    private String posterPublicId;
    private String trailerUrl;
    private String trailerPublicid;
    private List<ShowtimeDTO> showtime;
    private List<ImageDTO> image;
    private List<ActorDTO> actors;
    private List<CategoryDTO> categories;
}

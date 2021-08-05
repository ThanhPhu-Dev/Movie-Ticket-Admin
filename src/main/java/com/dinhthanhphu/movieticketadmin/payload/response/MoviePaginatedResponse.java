package com.dinhthanhphu.movieticketadmin.payload.response;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MoviePaginatedResponse {
    private List<MovieDTO> content;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
}

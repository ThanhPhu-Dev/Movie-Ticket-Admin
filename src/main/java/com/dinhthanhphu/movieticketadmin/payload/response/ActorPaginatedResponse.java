package com.dinhthanhphu.movieticketadmin.payload.response;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ActorPaginatedResponse {
    private List<ActorDTO> content;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
}

package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.ActorRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.ActorPaginatedResponse;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.utils.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActorAPI {

    @Autowired
    private CloudinaryUtils cloudUtil;

    @Autowired
    private IActorService actorService;

    @PostMapping(value = "/edit-actor")
    public ActorDTO editActor(@ModelAttribute ActorRequest formData) {
        ActorDTO dto;
        if(formData.getId() != null){
            dto = actorService.findById(formData.getId());
        }else{
            dto = new ActorDTO();
        }
        Map uploadResult = null;
        if (formData.getAvatar().getSize() != 0) {
            if(formData.getId() != null){
                uploadResult = cloudUtil.uploadPublicId(formData.getAvatar(), dto.getPublic_id());
            }else{
                uploadResult = cloudUtil.uploadCloudinary(formData.getAvatar(),"movieCinema/actor");
            }
            dto.setPublic_id(uploadResult.get("public_id").toString());
            dto.setPublic_url(uploadResult.get("url").toString());
        }
        dto.setBiography(formData.getBiography());
        dto.setName(formData.getName());
        return actorService.save(dto);
    }

    @GetMapping(value = {"/idol", "/idol/{name}"})
    public ActorPaginatedResponse findNameIdol(@PathVariable( required = false) String name){

        return findNameIdolPaginated(name, 1);
    }

    @GetMapping(value = {"/idol/page/{pageNo}", "/idol/{name}/page/{pageNo}"})
    public ActorPaginatedResponse findNameIdolPaginated(@PathVariable( required = false) String name,
                                                        @PathVariable int pageNo){
        int pageSize = 4;
        Page<ActorDTO> page = actorService.findByNameContainingPaginated(name, pageNo,pageSize);
        return ActorPaginatedResponse.builder()
                                     .content(page.getContent())
                                     .currentPage(pageNo)
                                     .totalItems(page.getTotalElements())
                                     .totalPages(page.getTotalPages())
                                     .build();
    }

    @GetMapping(value = "/allIdol")
    public List<ActorDTO> findAll(){
        List<ActorDTO> lst = actorService.findAll();
        return lst;
    }

    @DeleteMapping("/delete/actor/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable String id){
        boolean rs = actorService.delete(id);
        if(rs){
            return ResponseEntity.accepted().body("delete success");
        }
        return ResponseEntity.badRequest().body("delete fail");
    }
}

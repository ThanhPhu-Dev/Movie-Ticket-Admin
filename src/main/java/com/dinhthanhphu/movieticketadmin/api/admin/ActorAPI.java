package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.payload.ActorRequest;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.utils.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            uploadResult = cloudUtil.upload(formData.getAvatar(), "movieCinema/actor", dto.getPublic_id());
            dto.setPublic_id(uploadResult.get("public_id").toString());
            dto.setPublic_url(uploadResult.get("url").toString());
        }
        dto.setBiography(formData.getBiography());
        dto.setName(formData.getName());
        return actorService.save(dto);
    }

    @GetMapping(value = {"/idol", "/idol/{name}"})
    public List<ActorDTO> findNameIdol(@PathVariable( required = false) String name){
        List<ActorDTO> lst = actorService.findByNameContaining(name);
        return lst;
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

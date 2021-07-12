package com.dinhthanhphu.movieticketadmin.payload;

import com.cloudinary.StoredFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ActorRequest extends StoredFile {
    private String name;
    private MultipartFile avatar;
    private String biography;
}

package com.dinhthanhphu.movieticketadmin.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryUtils {
    private Cloudinary cloud;
    public Cloudinary getcloudinaryUtils() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "imagetp",
                "api_key", "864783699686865",
                "api_secret", "V7anAlaMywEENEqxLq0774IEL0A"
        ));
    }

    public CloudinaryUtils() {
        cloud = getcloudinaryUtils();
    }

    public Map upload(MultipartFile file , String folder) {
        Map uploadResult;
        try {
            uploadResult = cloud.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", folder == null ? "" : folder + "/"
                            ));
            return uploadResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map upload(MultipartFile file , String folder, String filename) {
        Map uploadResult;
        try {
            uploadResult = cloud.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", folder == null ? "" : folder + "/",
                            "public_id", filename));
            return uploadResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public Map<String,String> uploadImage(MultipartFile file, String folder){
//
//        Map<String,String> result = new HashMap<String, String>();
//        Map uploadResult = upload(file,folder);
//        result.put("public_id", (String) uploadResult.get("public_id"));
//        result.put("url", (String) uploadResult.get("url"));
//        return result;
//    }
//
//    public Map<String,String> uploadImage(MultipartFile file, String folder, String fileName){
//
//        Map<String,String> result = new HashMap<String, String>();
//        Map uploadResult = fileName != null ? upload(file,folder,fileName) : upload(file,folder);
//        result.put("public_id", (String) uploadResult.get("public_id"));
//        result.put("url", (String) uploadResult.get("url"));
//        return result;
//    }
}

package com.dinhthanhphu.movieticketadmin.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    /*thêm 1 file vào cloudinary*/
    @Async("asyncExecutor")
    public CompletableFuture<Map> uploadCloudinaryAsync(MultipartFile file, String folder) {
        return CompletableFuture.supplyAsync(() -> {
            Long t1 = System.currentTimeMillis();
            System.out.println("start upload: "+ t1);
            Map uploadResult;
            try {
                uploadResult = cloud.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", folder == null ? "" : folder + "/"
                        ));
                Long t2 = System.currentTimeMillis();
                System.out.println("end upload: "+ t2);
                System.out.println("tong upload: "+ (t2-t1));
                return uploadResult;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    /*thêm 1 file vào cloudinary*/
    public Map uploadCloudinary(MultipartFile file, String folder) {
        Map uploadResult;
        try {
            uploadResult = cloud.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", folder == null ? "" : folder + "/"
                    ));
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /*them nhieu file vao cloudinary*/
//    @Async("asyncExecutor")
//    public CompletableFuture<List<Map>> uploadCloudinaryAsync(MultipartFile[] file , String folder) {
//        return CompletableFuture.supplyAsync(() -> {
//            List<Map> rs = new ArrayList<>();
//            Map uploadResult = null;
//            try{
//                for (MultipartFile i : file) {
//                    uploadResult = cloud.uploader().upload(i.getBytes(),
//                            ObjectUtils.asMap(
//                                    "resource_type", "auto",
//                                    "folder", folder == null ? "" : folder + "/"
//                            ));
//                    rs.add(uploadResult);
//                }
//                return rs;
//            }catch (Exception e){
//                e.printStackTrace();
//                return null;
//            }
//        });
//    }


    /*update 1 file lên cloudinary*/
    @Async("asyncExecutor")
    public CompletableFuture<Map> uploadPublicIdAsync(MultipartFile file, String publicId) {
        return CompletableFuture.supplyAsync(() -> {
            Long t1 = System.currentTimeMillis();
            System.out.println("start update: "+ t1);
            System.out.println(publicId);
            Map uploadResult;
            try {
                uploadResult = cloud.uploader().upload(file.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "public_id", publicId));
                Long t2 = System.currentTimeMillis();
                System.out.println("end update: "+ t2);
                System.out.println("tong update: "+ (t2-t1));
                return uploadResult;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        });
    }

    /*update 1 file lên cloudinary*/
    public Map uploadPublicId(MultipartFile file, String publicId) {
        Map uploadResult;
        try {
            uploadResult = cloud.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "public_id", publicId));

            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*thêm 1 file byte lên cloudinary*/
    public Map uploadCloudinary(byte[] file, String folder) {
            Map uploadResult = null;
            try {
                uploadResult = cloud.uploader().upload(file,
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", folder == null ? "" : folder + "/"
                        ));
                return uploadResult;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
    }

    public boolean delete(String publicId) {
        try {
            cloud.uploader().destroy(publicId,
                    ObjectUtils.emptyMap());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

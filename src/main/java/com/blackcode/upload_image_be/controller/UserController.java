package com.blackcode.upload_image_be.controller;

import com.blackcode.upload_image_be.config.FileStorageService;
import com.blackcode.upload_image_be.dto.UserReq;
import com.blackcode.upload_image_be.dto.UserRes;
import com.blackcode.upload_image_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/getListAll")
    public ResponseEntity<List<UserRes>> getListAll(){
        try{
            List<UserRes> rtnUser = new ArrayList<>();
            List<UserRes> dataUser = userService.getListAll();
            for (UserRes tempData : dataUser){
                UserRes dataTemp = new UserRes();
                UserRes findUser = userService.getUserById(tempData.getUserId());
                dataTemp.setUserId(tempData.getUserId());
                dataTemp.setUserName(tempData.getUserName());
                dataTemp.setUserDesc(tempData.getUserDesc());

                if(findUser.getUserImage() != null && !findUser.getUserImage().isEmpty()){
                    String filename = findUser.getUserImage();
                    File file = new File(uploadDir + File.separator + filename);
                    if (file.exists()) {
                        String imageUrl = "http://localhost:8080/api/user/images/" + filename;
                        dataTemp.setUserImage(imageUrl);
                    } else {
                        dataTemp.setUserImage(null);
                    }
                }
                rtnUser.add(dataTemp);
            }

            return new ResponseEntity<List<UserRes>>(rtnUser, HttpStatus.OK);
        }catch (NoSuchElementException err){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            byte[] image = storageService.load(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getValueById/{id}")
    public ResponseEntity<UserRes> getValueById(@PathVariable("id") Long userId) {
        try {
            UserRes dataUser = userService.getUserById(userId);

            String filename = dataUser.getUserImage();
            File file = new File(uploadDir + File.separator + filename);
            if (file.exists()) {
                dataUser.setUserImage(filename);
            } else {
                dataUser.setUserImage(null);
            }
            return new ResponseEntity<>(dataUser, HttpStatus.OK);
        } catch (NoSuchElementException err) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserRes> createUser(
            @RequestParam("userName") String userName,
            @RequestParam("userDesc") String userDesc,
            @RequestParam("userImage") MultipartFile userImage){
        try {
            UserReq userReq = new UserReq();
            userReq.setUserName(userName);
            userReq.setUserDesc(userDesc);
            if (userImage != null && !userImage.isEmpty()) {
                String imagePath = storageService.store(userImage);
                userReq.setUserImage(imagePath);
            }
            UserRes dataUser = userService.createUser(userReq);
            return new ResponseEntity<>(dataUser, HttpStatus.OK);
        }catch (NoSuchElementException err){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserRes> updateUser(@PathVariable("id") Long userId,
                                              @RequestParam("userName") String userName,
                                              @RequestParam("userDesc") String userDesc,
                                              @RequestParam("userImageOld") String userImageOld,
                                              @RequestParam(value = "userImage", required = false) MultipartFile userImage){
        try {
            UserReq userReq = new UserReq();
            userReq.setUserName(userName);
            userReq.setUserDesc(userDesc);
            System.out.println("image :"+userImage);
            if (userImage != null && !userImage.isEmpty()) {
                UserRes dataUser = userService.getUserById(userId);
                if(dataUser.getUserImage() != null && !dataUser.getUserImage().isEmpty()){
                    System.out.println("Image Storage not null");
                    String filename = dataUser.getUserImage();
                    System.out.println("file : "+filename);
                    if(filename.equals(userImage.getOriginalFilename())){
                        System.out.println("image user tidak di ganti");
                    }else{
                        File file = new File(uploadDir + File.separator + filename);
                        System.out.println("file : "+file);
                        if (file.exists()) {
                            storageService.delete(dataUser.getUserImage());
                        } else {
                            System.out.println("Gambar Tidak Tersedia di Storage");
                        }
                        String imagePath = storageService.store(userImage);
                        userReq.setUserImage(imagePath);
                    }
                }else{
                    String imagePath = storageService.store(userImage);
                    userReq.setUserImage(imagePath);
                }
            }

            UserRes dataUser = userService.updateUser(userId, userReq);
            return new ResponseEntity<UserRes>(dataUser, HttpStatus.OK);
        }catch (NoSuchElementException err){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        try {
            System.out.println(userId);
            String rtn = userService.deleteUser(userId);
            return new ResponseEntity<String>(rtn, HttpStatus.OK);
        }catch (NoSuchElementException err){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

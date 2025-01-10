package com.blackcode.upload_image_be.controller;

import com.blackcode.upload_image_be.dto.UserRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/User")
public class UserController {

    @GetMapping("/getListAll")
    public ResponseEntity<List<UserRes>> getListAll(){
        return null;
    }

    @GetMapping("/getValueById/{id}")
    public ResponseEntity<UserRes> getValueById(){
        return null;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserRes> createUser(){
       return null;
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserRes> updateUser(){
        return null;
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserRes> deleteUser(){
        return null;
    }
}

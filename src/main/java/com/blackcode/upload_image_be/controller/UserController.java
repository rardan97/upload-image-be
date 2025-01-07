package com.blackcode.upload_image_be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/User")
public class UserController {

    @GetMapping("/getListAll")
    public ResponseEntity<List<String>> getListAll(){
        return null;
    }

    @GetMapping("/getValueById/{id}")
    public ResponseEntity<String> getValueById(){
        return null;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(){
       return null;
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(){
        return null;
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(){
        return null;
    }
}

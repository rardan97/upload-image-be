package com.blackcode.upload_image_be.controller;

import com.blackcode.upload_image_be.dto.UserReq;
import com.blackcode.upload_image_be.dto.UserRes;
import com.blackcode.upload_image_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getListAll")
    public ResponseEntity<List<UserRes>> getListAll(){
        List<UserRes> userResList = userService.getListAll();
        return new ResponseEntity<>(userResList, HttpStatus.OK);
    }

    @GetMapping("/getValueById/{id}")
    public ResponseEntity<UserRes> getValueById(@PathVariable("id") Long id){
        UserRes userRes = userService.getUserById(id);
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserRes> createUser(@RequestBody UserReq userReq){
        UserRes userRes = userService.createUser(userReq);
       return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserRes> updateUser(@PathVariable("id") Long id, @RequestBody UserReq userReq){
        UserRes userRes = userService.updateUser(id, userReq);
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        String rtn = userService.deleteUser(id);
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }
}

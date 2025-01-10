package com.blackcode.upload_image_be.service;

import com.blackcode.upload_image_be.dto.UserReq;
import com.blackcode.upload_image_be.dto.UserRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserRes> getListAll();

    UserRes getUserById(Long id);

    UserRes createUser(UserReq userReq);

    UserRes updateUser(Long id, UserReq userReq);

    String deleteUser(Long id);
}

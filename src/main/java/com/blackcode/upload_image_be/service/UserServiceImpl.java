package com.blackcode.upload_image_be.service;

import com.blackcode.upload_image_be.dto.UserReq;
import com.blackcode.upload_image_be.dto.UserRes;
import com.blackcode.upload_image_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserRes> getListAll(UserReq userReq) {
        return null;
    }

    @Override
    public UserRes getUserById(Long id) {
        return null;
    }

    @Override
    public UserRes createUser(UserReq userReq) {
        return null;
    }

    @Override
    public UserRes updateUser(Long id, UserReq userReq) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }
}

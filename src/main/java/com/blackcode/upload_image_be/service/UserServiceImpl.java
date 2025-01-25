package com.blackcode.upload_image_be.service;

import com.blackcode.upload_image_be.dto.UserReq;
import com.blackcode.upload_image_be.dto.UserRes;
import com.blackcode.upload_image_be.model.User;
import com.blackcode.upload_image_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserRes> getListAll() {
        List<User> userList = userRepository.findAll();
        List<UserRes> userRtn = new ArrayList<>();
        for (User userTemp : userList){
            UserRes userRtnTemp = new UserRes();
            userRtnTemp.setUserId(userTemp.getUserId());
            userRtnTemp.setUserName(userTemp.getUserName());
            userRtnTemp.setUserImage(userTemp.getUserImage());
            userRtnTemp.setUserDesc(userTemp.getUserDesc());
            userRtn.add(userRtnTemp);
        }
        return userRtn;
    }

    @Override
    public UserRes getUserById(Long id) {
        Optional<User> userData = userRepository.findById(id);
        User userTemp = userData.get();
        UserRes userRtn = new UserRes();
        userRtn.setUserId(userTemp.getUserId());
        userRtn.setUserName(userTemp.getUserName());
        userRtn.setUserImage(userTemp.getUserImage());
        userRtn.setUserDesc(userTemp.getUserDesc());
        return userRtn;
    }

    @Override
    public UserRes createUser(UserReq userReq) {
        User user = new User();
        user.setUserName(userReq.getUserName());
        user.setUserImage(userReq.getUserImage());
        user.setUserDesc(userReq.getUserDesc());
        User userSave = userRepository.save(user);
        UserRes userRes = new UserRes();
        userRes.setUserId(userSave.getUserId());
        userRes.setUserName(userSave.getUserName());
        userRes.setUserImage(userSave.getUserImage());
        userRes.setUserDesc(userSave.getUserDesc());
        return userRes;
    }

    @Override
    public UserRes updateUser(Long id, UserReq userReq) {
        Optional<User> userData = userRepository.findById(id);
        if(userData.isPresent()){
            User userTemp = userData.get();
            User userUpdate = new User();
            userUpdate.setUserId(userTemp.getUserId());
            userUpdate.setUserName(userReq.getUserName());
            if(userReq.getUserImage() != null){
                userUpdate.setUserImage(userReq.getUserImage());
            }else{
                userUpdate.setUserImage(userTemp.getUserImage());
            }
            userUpdate.setUserDesc(userReq.getUserDesc());
            User userSave = userRepository.save(userUpdate);
            UserRes userRtn = new UserRes();
            userRtn.setUserId(userSave.getUserId());
            userRtn.setUserName(userSave.getUserName());
            userRtn.setUserImage(userSave.getUserImage());
            userRtn.setUserDesc(userSave.getUserDesc());
            return userRtn;
        }
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> dataUser = userRepository.findById(id);
        if(dataUser.isPresent()){
            userRepository.deleteById(id);
            return "Success Delete User ID : "+id;
        }else {
            return "Not Found User ID : "+id;
        }
    }
}

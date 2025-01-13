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
            userRtnTemp.setId(userTemp.getId());
            userRtnTemp.setName(userTemp.getName());
            userRtnTemp.setImage(userTemp.getImage());
//            userRtnTemp.setEmail(userTemp.getEmail());
            userRtn.add(userRtnTemp);
        }
        return userRtn;
    }

    @Override
    public UserRes getUserById(Long id) {
        Optional<User> userData = userRepository.findById(id);
        User userTemp = userData.get();
        UserRes userRtn = new UserRes();
        userRtn.setId(userTemp.getId());
        userRtn.setName(userTemp.getName());
//        userRtn.setEmail(userTemp.getEmail());
        userRtn.setImage(userTemp.getImage());
        return userRtn;
    }

    @Override
    public UserRes createUser(UserReq userReq) {
        User user = new User();
        user.setName(userReq.getName());
//        user.setEmail(userReq.getEmail());
        user.setImage(userReq.getImage());
        User userSave = userRepository.save(user);
        UserRes userRet = new UserRes();
        userRet.setId(userSave.getId());
        userRet.setName(userSave.getName());
//        userRet.setEmail(userSave.getEmail());
        userRet.setImage(userSave.getImage());
        return userRet;
    }

    @Override
    public UserRes updateUser(Long id, UserReq userReq) {
        Optional<User> userData = userRepository.findById(id);
        User userTemp = userData.get();

        User userUpdate = new User();
        userUpdate.setId(userTemp.getId());
        userUpdate.setName(userTemp.getName());
//        userUpdate.setEmail(userTemp.getEmail());
        userUpdate.setImage(userTemp.getImage());
        User userSave = userRepository.save(userUpdate);

        UserRes userRtn = new UserRes();
        userRtn.setId(userSave.getId());
        userRtn.setName(userSave.getName());
//        userRtn.setEmail(userSave.getEmail());
        userRtn.setImage(userSave.getImage());

        return userRtn;
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "success delete";
    }
}

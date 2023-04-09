package com.myapp.example.service;

import com.myapp.example.exception.UserException;
import com.myapp.example.model.UserData;
import com.myapp.example.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private static final String[] cityNames = {"Delhi","Mumbai","Chennai","Jaipur","Kolkata"};

    @Autowired
    private UserDataRepository userDataRepository;
    public UserData addUserData(Long id){
        Optional<UserData> userDataOptional = userDataRepository.findById(id);
        if(userDataOptional.isPresent()){
            throw new UserException("User Already Added");
        }
        UserData savedData=null;
        Random ran = new Random();
        int age = ran.nextInt(10) + 20;

        int index = ran.nextInt(5);
        UserData userData = new UserData();
        userData.setId(id);
        userData.setAge(age);
        userData.setCity(cityNames[index]);
        userData.setPhoneNumber(900000000L+Math.abs(ran.nextLong()));

        try {
            log.info("Saving data for user::{}",userData);
            savedData  = userDataRepository.save(userData);
        }
        catch (Exception e){
            log.error("Exception occurred while saving",e);
            throw new UserException("Error While Saving",e);
        }
        return savedData;
    }

    public UserData updateUserData(UserData userData){
        log.info("Update Request::{}",userData);
        Long id = userData.getId();
        Optional<UserData> userDataOptional = userDataRepository.findById(id);
        if(!userDataOptional.isPresent()){
            throw new UserException("User Not Found");
        }
        UserData updatedData = null;
        try{
           updatedData =  userDataRepository.save(userData);
           log.info("User::{} Data Updated",id);
        }
        catch (Exception e){
            log.error("Error Occurred while updating",e);
            throw new UserException("Error While Updating",e);
        }
        return updatedData;
    }

    public UserData getUserData(Long id){
        log.info("Fetching data for user::{}",id);
        Optional<UserData> userDataOptional = userDataRepository.findById(id);
        if(!userDataOptional.isPresent()){
            throw new UserException("User Not Found");
        }
        return userDataOptional.get();
    }

    public void deleteUser(Long id){
        Optional<UserData> userDataOptional = userDataRepository.findById(id);
        if(!userDataOptional.isPresent()){
            throw new UserException("User Not Found");
        }
        log.info("User::{} deleted",id);
        userDataRepository.deleteById(id);
    }

    public List<UserData> getAllusers() {
        log.info("Getting all data");
        return userDataRepository.findAll();
    }
}

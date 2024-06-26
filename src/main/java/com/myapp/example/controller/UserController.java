package com.myapp.example.controller;

import com.myapp.example.model.UserData;
import com.myapp.example.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<UserData> addNewUser(@Valid @NotNull(message = "User Id Cannot Be Null")  @PathVariable("userId") Long userId){
        UserData response =  userService.addUserData(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserData> getUser(@Valid @NotNull(message = "User Id Cannot Be Null") @PathVariable("userId") Long userId){
        UserData response =  userService.getUserData(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserData> updateUser(@Valid @RequestBody UserData userData){
        UserData response = userService.updateUserData(userData);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@Valid @NotNull(message = "User Id Cannot Be Null") @PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("DELETED "+id,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserData>> getAllUsers(){
        log.info("Getting all users");
        return new ResponseEntity<>(userService.getAllusers(),HttpStatus.OK);
    }
}

package com.gmo.gmo.controllers;

import com.gmo.gmo.common.MyResponse;
import com.gmo.gmo.entities.User;
import com.gmo.gmo.services.UserService;
import com.gmo.gmo.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("")
    public ResponseEntity<MyResponse> addUser(@RequestBody  User user){
        //init info
        MyResponse response = new MyResponse();
        User currentUser = userService.save(user);
        response.setTimeStamp(System.currentTimeMillis());

        if (currentUser == null){
            response.setMessage("Tên người dùng đã tồn tại");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        response.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("")
    public String getUser(){
       return "hi";
    }

}

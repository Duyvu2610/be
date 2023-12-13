package com.gmo.gmo.controllers;

import com.gmo.gmo.common.MyResponse;
import com.gmo.gmo.entities.User;
import com.gmo.gmo.services.UserService;
import com.gmo.gmo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")

    public ResponseEntity<MyResponse> login(@RequestBody User user){
        MyResponse response = new MyResponse();
        if (userService.login(user)){
            response.setData(true);
            return ResponseEntity.ok(response);
        }
        response.setData(false);
        response.setStatus(400);
        response.setMessage("Tài khoản hoặc mật khẩu không chính xác");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<MyResponse> register(@RequestBody User user){
        MyResponse response = new MyResponse();
        User newUser = userService.save(user);
        if (newUser != null){
            response.setData(newUser);
            return ResponseEntity.ok(response);
        }
        response.setData(false);
        response.setStatus(400);
        response.setMessage("Tài khoản đã tồn tại");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

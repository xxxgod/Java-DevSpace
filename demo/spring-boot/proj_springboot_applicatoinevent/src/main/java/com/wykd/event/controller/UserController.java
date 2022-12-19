package com.wykd.event.controller;

import com.wykd.event.service.UserService;
import com.wykd.event.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 功能：
 * Created by [Alex]
 * 2020/6/16 14:52
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("publish")
    public String publish(@RequestParam String name){

        User user = new User();
        user.setName(name);
        userService.register(user);

        return new HashMap().toString();
    }

}

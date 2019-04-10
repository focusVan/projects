package com.focus.spring.controller;

import com.focus.spring.dto.UserDTO;
import com.focus.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * focus Create in 15:15 2019/3/29
 */
@RestController
@RequestMapping("/focus/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/add")
    public void addUser(){
        userService.add(new UserDTO());
    }
}

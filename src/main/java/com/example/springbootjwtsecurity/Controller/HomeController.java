package com.example.springbootjwtsecurity.Controller;


import com.example.springbootjwtsecurity.Model.Customers;
import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {
@Autowired
private UserServices userService;
    @GetMapping
    public String home(){
        return "this a secure Api";
    }

    @GetMapping("list")
    public List<User> getAllusers(){
        return userService.getAllusers();
    }

}

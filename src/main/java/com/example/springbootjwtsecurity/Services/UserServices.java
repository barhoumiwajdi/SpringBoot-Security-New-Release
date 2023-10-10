package com.example.springbootjwtsecurity.Services;


import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices {
@Autowired
private UserRepository userrepo;
    public List<User> getAllusers(){

        return  userrepo.findAll();

    }
}

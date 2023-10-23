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
    public List<User> getUser() {
        return userrepo.findAll();
    }


    public User getUserByid(int id) {
        return userrepo.findById(id).get();
    }


    public User updateUser(int id, User user) {
        User user1 = userrepo.findById(id).get();
        user1.setId(id);
        return userrepo.save(user1);
    }


    public void deleteUser(int id) {
        userrepo.deleteById(id);
    }
}

package com.example.springbootjwtsecurity.Services;


import com.example.springbootjwtsecurity.Exception.CustomerNotFoundException;
import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {
@Autowired
private UserRepository userrepo;
    public List<User> getAllusers(){

        return  userrepo.findAll();

    }



    public User getUserByid(int id) {
        Optional<User> found = userrepo.findById(id);
        if (found.isEmpty()){
            throw new CustomerNotFoundException("customer not found");

        } else
        {
            return found.get();
        }
    }


    public User updateUser(int id, User user) {
         Optional<User> found =  userrepo.findById(id);
             if (found.isPresent()) {
                  user.setId(id);
                return userrepo.save(user);
}
          else {
                  throw  new CustomerNotFoundException("no USer With this Id will be updated try to verify your data ");
               }

               }


    public void deleteUser(int id) {
        userrepo.deleteById(id);
    }
}

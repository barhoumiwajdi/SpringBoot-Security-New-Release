package com.example.springbootjwtsecurity.Controller;
import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private UserServices userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        System.out.println("Users..");
        return userService.getAllusers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserByid(id);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody User user){
        userService.updateUser(id, user);
        return "User Updated Successfully...";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "User Deleted Successfully...";
    }
}

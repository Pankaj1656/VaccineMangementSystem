package com.example.VaccineMangementSystem.Controllers;


import com.example.VaccineMangementSystem.Modals.User;
import com.example.VaccineMangementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
       return userService.addUser(user);
    }
    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate(@RequestParam("userId")Integer userId){
        return userService.getVaccDate(userId);
    }
}

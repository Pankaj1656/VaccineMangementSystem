package com.example.VaccineMangementSystem.Service;

import com.example.VaccineMangementSystem.Modals.Dose;
import com.example.VaccineMangementSystem.Modals.User;
import com.example.VaccineMangementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User addUser(User user) {
       user= userRepository.save(user);
       return user;
    }

    public Date getVaccDate(Integer userId) {
        User user=userRepository.findById(userId).get();

        Dose dose=user.getDose();
       return  dose.getVaccinationDate();
    }
}

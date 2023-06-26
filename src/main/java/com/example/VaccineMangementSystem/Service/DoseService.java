package com.example.VaccineMangementSystem.Service;

import com.example.VaccineMangementSystem.Modals.Dose;
import com.example.VaccineMangementSystem.Modals.User;
import com.example.VaccineMangementSystem.Repository.DoseRepository;
import com.example.VaccineMangementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;
    @Autowired
    UserRepository userRepository;
    public String giveDose(String doseId, Integer userId) {
        User user=userRepository.findById(userId).get();

        Dose dose =new Dose();
        dose.setDoseId(doseId);
        dose.setUser(user);

       user.setDose(dose);

       userRepository.save(user);

     return ("Dose given to user");
    }
}

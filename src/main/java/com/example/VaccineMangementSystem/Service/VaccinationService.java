package com.example.VaccineMangementSystem.Service;

import com.example.VaccineMangementSystem.Modals.VaccinationCenter;
import com.example.VaccineMangementSystem.Repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(vaccinationCenter.getAddress()==null){
            throw new RuntimeException("Address not Found");
        }
        vaccinationCenterRepository.save(vaccinationCenter);
        return "Vaccination center added at location"+" "+vaccinationCenter.getAddress();
    }
}

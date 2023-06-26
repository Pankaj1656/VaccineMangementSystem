package com.example.VaccineMangementSystem.Transformers;

import com.example.VaccineMangementSystem.Modals.Doctor;

public class DoctorTransformer {
    public  static Doctor setDoctor(Integer age,String  name){
        Doctor doctor=Doctor.builder().age(age).name(name).build();
        return doctor;
    }
}

package com.example.VaccineMangementSystem.Repository;

import com.example.VaccineMangementSystem.Modals.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    public Doctor findByEmailId(String emailId);
}

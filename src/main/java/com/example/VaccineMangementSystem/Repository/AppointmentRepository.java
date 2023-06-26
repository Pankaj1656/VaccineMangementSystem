package com.example.VaccineMangementSystem.Repository;

import com.example.VaccineMangementSystem.Modals.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}

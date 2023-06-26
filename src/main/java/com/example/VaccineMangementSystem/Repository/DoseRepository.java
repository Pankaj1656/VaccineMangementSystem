package com.example.VaccineMangementSystem.Repository;

import com.example.VaccineMangementSystem.Modals.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose,Integer> {
}

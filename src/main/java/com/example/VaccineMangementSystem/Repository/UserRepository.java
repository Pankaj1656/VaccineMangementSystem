package com.example.VaccineMangementSystem.Repository;

import com.example.VaccineMangementSystem.Modals.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

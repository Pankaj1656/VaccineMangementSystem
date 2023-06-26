package com.example.VaccineMangementSystem.Modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="vaccinationCenter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String centerName;

    private LocalTime OpeningTime;

    private LocalTime ClosingTime;

    private String address;

    private int doseCapacity;

    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter" , cascade = CascadeType.ALL)
    private List<Doctor> doctorList=new ArrayList<>();

}

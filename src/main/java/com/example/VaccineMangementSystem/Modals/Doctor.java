package com.example.VaccineMangementSystem.Modals;

import com.example.VaccineMangementSystem.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Doctors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;

    private String name;

    private int age;

    @Column(unique = true)
    private String emailId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private VaccinationCenter vaccinationCenter;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL)
    private List<Appointment>  appointmentList=new ArrayList<>();





}

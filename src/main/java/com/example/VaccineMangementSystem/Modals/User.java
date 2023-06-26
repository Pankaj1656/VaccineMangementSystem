package com.example.VaccineMangementSystem.Modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String name;

    private int age;

    @Column(unique = true)
    private String emailId;

    private  String mobileNo;

    private String gender;

    @JsonIgnore
    @OneToOne(mappedBy = "user" ,cascade =CascadeType.ALL)
    private Dose dose;
    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Appointment> appointmentList=new ArrayList<>();



}

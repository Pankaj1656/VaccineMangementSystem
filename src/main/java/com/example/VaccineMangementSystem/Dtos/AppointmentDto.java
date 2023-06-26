package com.example.VaccineMangementSystem.Dtos;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AppointmentDto {
    private int userId;

    private int docId;

    private Date appointmentDate;

    private LocalTime appointmentTime;
}

package com.example.VaccineMangementSystem.Controllers;

import com.example.VaccineMangementSystem.Dtos.AppointmentDto;
import com.example.VaccineMangementSystem.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestBody AppointmentDto appointmentDto){
        try{
            String res=appointmentService.bookAppointment(appointmentDto);
            return  res;
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}

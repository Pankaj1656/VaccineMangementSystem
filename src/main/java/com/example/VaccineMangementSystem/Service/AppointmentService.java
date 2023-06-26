package com.example.VaccineMangementSystem.Service;

import com.example.VaccineMangementSystem.Dtos.AppointmentDto;
import com.example.VaccineMangementSystem.Exceptions.DoctorNotFound;
import com.example.VaccineMangementSystem.Exceptions.UserNotFound;
import com.example.VaccineMangementSystem.Modals.Appointment;
import com.example.VaccineMangementSystem.Modals.Doctor;
import com.example.VaccineMangementSystem.Modals.User;
import com.example.VaccineMangementSystem.Repository.AppointmentRepository;
import com.example.VaccineMangementSystem.Repository.DoctorRepository;
import com.example.VaccineMangementSystem.Repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private JavaMailSender emailSender;

    public String bookAppointment(AppointmentDto appointmentDto) throws DoctorNotFound , UserNotFound {
        Integer id=appointmentDto.getUserId();
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw  new UserNotFound("User not found");
        }
        Integer val=appointmentDto.getDocId();
        Optional<Doctor> doctorOptional=doctorRepository.findById(val);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFound("Doctor not found");
        }

        Doctor doctor=doctorOptional.get();
        User user=userOptional.get();
        Appointment appointment=new Appointment();

        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setUser(user);

        appointment=appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(appointment);
        user.getAppointmentList().add(appointment);

        doctorRepository.save(doctor);
        userRepository.save(user);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        String body = " Hi ! "+user.getName()+"\n" +
                "You have successfully booked an appointment on "+appointment.getAppointmentDate() + "at "+appointment.getAppointmentTime()+"\n"+
                "You doctor is "+doctor.getName()+ "\n"+
                "Please reach at "+doctor.getVaccinationCenter().getAddress()+"\n"
                + "Mask is mandatory";
        mailMessage.setFrom("chauhan165699@gmail.com");
        mailMessage.setTo(user.getEmailId());
        mailMessage.setSubject("Appointment Confirmed !! ");
        mailMessage.setText(body);
        emailSender.send(mailMessage);
        return "Appointment booked successfuly";
    }
}

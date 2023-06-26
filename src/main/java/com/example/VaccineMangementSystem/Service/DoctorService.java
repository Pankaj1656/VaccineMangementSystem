package com.example.VaccineMangementSystem.Service;

import com.example.VaccineMangementSystem.Dtos.AssociateDocDto;
import com.example.VaccineMangementSystem.Enums.Gender;
import com.example.VaccineMangementSystem.Exceptions.CenterNotFound;
import com.example.VaccineMangementSystem.Exceptions.DocAlreadyExistException;
import com.example.VaccineMangementSystem.Exceptions.DoctorNotFound;
import com.example.VaccineMangementSystem.Exceptions.EmailIdEmptyException;
import com.example.VaccineMangementSystem.Modals.Doctor;
import com.example.VaccineMangementSystem.Modals.VaccinationCenter;
import com.example.VaccineMangementSystem.Repository.DoctorRepository;
import com.example.VaccineMangementSystem.Repository.VaccinationCenterRepository;
import com.example.VaccineMangementSystem.Transformers.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public String addDoctor(Doctor doctor)throws EmailIdEmptyException, DocAlreadyExistException {
        if(doctor.getEmailId()==null){
          throw new EmailIdEmptyException("Email id not Present");
        }
        if(doctorRepository.findByEmailId(doctor.getEmailId())!=null){
            throw  new DocAlreadyExistException("Doctor with given email id already exists");
        }
        doctorRepository.save(doctor);
        return "Doctor has been added to the database";
    }

    public String associateDocToCenter(AssociateDocDto associateDocDto) throws DoctorNotFound, CenterNotFound {
        Integer docId=associateDocDto.getDocId();
        Optional<Doctor> doctorOptional=doctorRepository.findById(docId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFound("Doctor with given id not present");
        }
        Integer centerId=associateDocDto.getCenterId();
        Optional<VaccinationCenter> centerOptional= vaccinationCenterRepository.findById(centerId);
        if(centerOptional.isEmpty()){
            throw new CenterNotFound("Center with id not found");
        }

        Doctor doctor=doctorOptional.get();
        VaccinationCenter vaccinationCenter=centerOptional.get();

        doctor.setVaccinationCenter(vaccinationCenter);
        vaccinationCenter.getDoctorList().add(doctor);

        vaccinationCenterRepository.save(vaccinationCenter);
       return "Doctor associated to given center";
    }

    public List<Doctor> getDocsAtCenter(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center Not Found");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctorList();

        return doctorList;

    }

    public List<Doctor> getMaleDocsAtCenter(Integer centerId) throws CenterNotFound {
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center not found");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctorList();
        List<Doctor> doctorList1=new ArrayList<>();
        for(Doctor doctor: doctorList){
            if(doctor.getGender()== Gender.MALE){
                doctorList1.add(doctor);
            }
        }

        return doctorList1;
    }

    public List<Doctor> getFemaleDocsAtCenter(Integer centerId) throws CenterNotFound{
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center not found");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctorList();
        List<Doctor> doctorList1=new ArrayList<>();
        for(Doctor doctor: doctorList){
            if(doctor.getGender()== Gender.FEMALE){
                doctorList1.add(doctor);
            }
        }

        return doctorList1;
    }

    public List<Doctor> getMaleDocsAboveAgeAtCenter(Integer centerId)  throws CenterNotFound{
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFound("Center not found");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctorList();
        List<Doctor> doctorList1=new ArrayList<>();
        for(Doctor doctor: doctorList){
            if(doctor.getGender()== Gender.MALE && doctor.getAge()>40){
                doctorList1.add(doctor);
            }
        }

        return doctorList1;
    }

    public List<Doctor> docsWith10Appointments() {
        List<Doctor> doctorList=doctorRepository.findAll();
        List<Doctor> doctorList1=new ArrayList<>();
        for (Doctor doctor:doctorList){
           if(doctor.getAppointmentList().size()>10){
               doctorList1.add(doctor);
           }
        }
        return doctorList1;
    }

    public List<Doctor> getMaleDocsAbove40() {
        List<Doctor> doctorList=doctorRepository.findAll();
        List<Doctor> doctorList1=new ArrayList<>();
        for (Doctor doctor:doctorList){
            if(doctor.getAge()>40){
                doctorList1.add(doctor);
            }
        }
        return doctorList1;
    }

    public Double maleToFemaleDocsRatio() {
        List<Doctor> doctorList=doctorRepository.findAll();
        Integer maleDoctor=0;
        Integer femaleDoctor=0;
        for(Doctor doctor:doctorList){
            if(doctor.getGender()==Gender.MALE){
                maleDoctor++;
            }
            else if(doctor.getGender()==Gender.FEMALE){
                femaleDoctor++;
            }
        }
        Double ratio= Double.valueOf(maleDoctor/femaleDoctor);
        return  ratio;

    }

    public void updateDetailsOfDocs(String emailId,String name , Integer age) throws DoctorNotFound {
       Doctor doctor=doctorRepository.findByEmailId(emailId);
       if(doctor==null){
           throw new DoctorNotFound("Doctor not found");
       }
//      Doctor  doctor= DoctorTransformer.setDoctor(age,name);
        doctor.setName(name);
        doctor.setAge(age);

       doctorRepository.save(doctor);


    }
}

package com.example.VaccineMangementSystem.Controllers;

import com.example.VaccineMangementSystem.Dtos.AssociateDocDto;
import com.example.VaccineMangementSystem.Exceptions.CenterNotFound;
import com.example.VaccineMangementSystem.Exceptions.DocAlreadyExistException;
import com.example.VaccineMangementSystem.Exceptions.DoctorNotFound;
import com.example.VaccineMangementSystem.Exceptions.EmailIdEmptyException;
import com.example.VaccineMangementSystem.Modals.Doctor;
import com.example.VaccineMangementSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/addDoctor")
    public String  addDoctor(@RequestBody Doctor doctor) throws DocAlreadyExistException, EmailIdEmptyException {
       try{
          String response= doctorService.addDoctor(doctor);
          return response;
       }
       catch (Exception e){
           return e.getMessage();
       }
    }
    @PostMapping("/associateDocToCenter")
    public ResponseEntity<String> associateDocToCenter(@RequestBody AssociateDocDto associateDocDto){
        try{
            String res=doctorService.associateDocToCenter(associateDocDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getDocsAtCenter")
    public ResponseEntity<List>  getDocsAtCenter(@RequestParam ("Id") Integer centerId) throws CenterNotFound {
        try{
            List<Doctor> doctorList= doctorService.getDocsAtCenter(centerId);
            return new ResponseEntity<>(doctorList,HttpStatus.OK);
        }
        catch (CenterNotFound e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getMaleDocsAtCenter")
    public ResponseEntity<List> getMaleDocsAtCenter(@RequestParam  ("Id") Integer centerId) throws CenterNotFound {
           try{
               List<Doctor> doctorList= doctorService.getMaleDocsAtCenter(centerId);
               return new ResponseEntity<>(doctorList,HttpStatus.OK);

           }
           catch (CenterNotFound e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
           }

    }
    @GetMapping("/getFemaleDocsAtCenter")
    public ResponseEntity<List> getFemaleDocsAtCenter(@RequestParam  ("Id") Integer centerId) throws CenterNotFound {
        try{
            List<Doctor> doctorList= doctorService.getFemaleDocsAtCenter(centerId);
            return new ResponseEntity<>(doctorList,HttpStatus.OK);

        }
        catch (CenterNotFound e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/getMaleDocsAboveAgeAtCenter")
    public ResponseEntity<List> getMaleDocsAboveAgeAtCenter(@RequestParam  ("Id") Integer centerId) throws CenterNotFound {
        try{
            List<Doctor> doctorList= doctorService.getMaleDocsAboveAgeAtCenter(centerId);
            return new ResponseEntity<>(doctorList,HttpStatus.OK);

        }
        catch (CenterNotFound e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/docsWith10Appointments")
    public ResponseEntity<List> docsWith10Appointments()  {

            List<Doctor> doctorList= doctorService.docsWith10Appointments();
            return new ResponseEntity<>(doctorList,HttpStatus.OK);
    }

    @GetMapping("/getMaleDocsAbove40")
    public ResponseEntity<List> getMaleDocs()  {

            List<Doctor> doctorList= doctorService.getMaleDocsAbove40();
            return new ResponseEntity<>(doctorList,HttpStatus.OK);

    }
    @GetMapping("/maleToFemaleDocsRatio")
    public ResponseEntity<Double> maleToFemaleDocsRatio() {

        Double ratio= doctorService.maleToFemaleDocsRatio();
        return new ResponseEntity<>(ratio,HttpStatus.OK);

    }
    @PutMapping("/updateDetailsOfDocs")
    public ResponseEntity<String> updateDetailsOfDocs(@RequestParam ("emailId") String emailId, @RequestParam ("name") String name, @RequestParam ("age") Integer age) {
        try{
             doctorService.updateDetailsOfDocs(emailId,name,age);
            return new ResponseEntity<>("Doctor details updated successfully",HttpStatus.OK);
        }
        catch (DoctorNotFound e){
            return new ResponseEntity<>("Doctor not found with given email",HttpStatus.NOT_FOUND);
        }


    }

}

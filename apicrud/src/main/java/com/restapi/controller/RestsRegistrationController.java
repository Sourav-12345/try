package com.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.dto.RegistrationDto;
import com.restapi.entities.Registration;
import com.restapi.repository.RegistrationRepository;

@RestController
@RequestMapping("/api/reg/new")
public class RestsRegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;
    //http://localhost:8080/api/reg/new
    //In jason the content is stored as a key value pair.
    //get mapping helps to convert the java objects into jason.
    @GetMapping
    public List<Registration> getAllRegistrations(){
        List<Registration> registrations = registrationRepository.findAll();
        return registrations;
    }
//    @GetMapping
//    public ResponseEntity<Registration> getRegistration(){
//
//        Optional<Registration> val = registrationRepository.findById(2L);
//        Registration reg = val.get();
//        return new ResponseEntity<Registration>(reg, HttpStatus.OK);
//    }
    //@RequestBody-It helps to copy the content of json to registration object.
    //ResponseEntity-it is used as a return type while returning the reponse to the  postman like status 201 etc.
    //<Registration>-here it is used as Registration object
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        Registration savedRegistration = registrationRepository.save(registration);
        return new ResponseEntity<>(savedRegistration,HttpStatus.CREATED);
    }
    //http://localhost:8080/api/reg/id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable long id){
        registrationRepository.deleteById(id);
        return new ResponseEntity<>("Rahul record is Deleted!!", HttpStatus.OK);
    }
    //http://localhost:/8080/api/reg?id=1
    @PutMapping
    public ResponseEntity<Registration>updateRegistration(@RequestParam long id, @RequestBody RegistrationDto  dto){
        Registration registration=registrationRepository.findById(id).get();
        registration.setFirstName(dto.getFirstName());
        registration.setLastName(dto.getLastName());
        registration.setEmail(dto.getEmail());
        registration.setMobile(dto.getMobile());
        Registration savedRegistration=registrationRepository.save(registration);
        return new ResponseEntity<>(savedRegistration, HttpStatus.OK);
    }

}

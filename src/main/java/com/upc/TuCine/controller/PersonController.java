package com.upc.TuCine.controller;

import com.upc.TuCine.dto.PersonDto;
import com.upc.TuCine.dto.TypeUserDto;
import com.upc.TuCine.service.PersonService;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons/{id}/typeUser
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/persons/{id}/typeUser")
    public ResponseEntity<TypeUserDto> getTypeUserByPersonId(@PathVariable("id") Integer id) {
        TypeUserDto typeUserDto= personService.getTypeUserByPersonId(id);
        if (typeUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(typeUserDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/persons
    //Method: POST
    @Transactional
    @PostMapping("/persons")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
        validatePerson(personDto);
        existsByPersonEmail(personDto.getEmail());
        existByPersonDni(personDto.getNumberDni());
        return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
    }

    void validatePerson(PersonDto person) {
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new ResourceValidationException("El nombre de la persona es obligatorio");
        }
        if(person.getLastName()==null || person.getLastName().isEmpty()){
            throw new ResourceValidationException("El apellido de la persona es obligatorio");
        }
        if(person.getNumberDni()==null || person.getNumberDni().isEmpty()){
            throw new ResourceValidationException("El DNI de la persona es obligatorio");
        }
        if(person.getEmail()==null || person.getEmail().isEmpty()){
            throw new ResourceValidationException("El email de la persona es obligatorio");
        }
        if(person.getPassword()==null || person.getPassword().isEmpty()){
            throw new ResourceValidationException("La contraseña de la persona es obligatorio");
        }
        if(person.getBirthdate()==null){
            throw new ResourceValidationException("La fecha de nacimiento de la persona es obligatorio");
        }
        if(person.getGender()==null ){
            throw new ResourceValidationException("El género de la persona es obligatorio");
        }
        if(person.getPhone()==null || person.getPhone().isEmpty()){
            throw new ResourceValidationException("El teléfono de la persona es obligatorio");
        }
        if(person.getTypeUser()==null){
            throw new ResourceValidationException("El tipo de usuario de la persona es obligatorio");
        }
    }

    void existsByPersonEmail(String email) {
        if (personService.existsByPersonEmail(email)) {
            throw new ResourceValidationException("Ya existe una persona registrada con ese email");
        }
    }

    void existByPersonDni(String dni) {
        if (personService.existsPersonByNumberDni(dni)) {
            throw new ResourceValidationException("Ya existe una persona registrada con ese DNI");
        }
    }
}

package com.upc.TuCine.service;

import com.upc.TuCine.dto.PersonDto;
import com.upc.TuCine.dto.TypeUserDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAllPersons();

    TypeUserDto getTypeUserByPersonId(Integer id);

    PersonDto createPerson(PersonDto personDto);

    boolean existsByPersonEmail (String email);

    boolean existsPersonByNumberDni(String numberDni);
}

package com.upc.TuCine.dto;

import com.upc.TuCine.model.Gender;
import com.upc.TuCine.model.TypeUser;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phone;
    private String photo;
    private String email;
    private String password;
    private String numberDni;
    private Gender gender;
    private TypeUser typeUser;
}

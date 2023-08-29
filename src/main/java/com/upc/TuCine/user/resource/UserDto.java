package com.upc.TuCine.user.resource;

import lombok.Data;

import java.time.LocalDate;

import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.entity.TypeUser;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private LocalDate emailVerified;
    private String password;
    private String imageSrc;
    private String phoneNumber;

    private Gender gender;
    private TypeUser typeUser;
}

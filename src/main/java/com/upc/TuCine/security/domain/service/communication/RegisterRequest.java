package com.upc.TuCine.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=60)
    private String lastname;

    @NotNull
    @NotBlank
    @Size(max=80)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(max=9)
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(max=100)
    private String password;

    @NotNull
    private LocalDate birthdate;

    private Set<String> typeUser;
    private Set<String> gender;
}

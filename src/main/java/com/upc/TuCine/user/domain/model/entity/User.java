package com.upc.TuCine.user.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.entity.TypeUser;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.time.LocalDate;

@Getter
@Setter
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastname", length = 60, nullable = false)
    private String lastname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "email_verified", nullable = false)
    private LocalDate emailVerified;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "image_src", length = 100, nullable = true)
    private String imageSrc;

    @Column(name = "phoneNumber", length = 9, nullable = false, unique = true)
    private String phoneNumber;

    //Relationships
    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "type_user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TypeUser typeUser;

}

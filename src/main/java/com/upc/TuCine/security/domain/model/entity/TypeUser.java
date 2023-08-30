package com.upc.TuCine.security.domain.model.entity;

import com.upc.TuCine.security.domain.model.enumeration.TypeUsers;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_user")

public class TypeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //For connection
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20, nullable = false)
    @Schema(description = "User type", example = "CINEPHILE")
    private TypeUsers name;
}

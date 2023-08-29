package com.upc.TuCine.security.domain.model.entity;

import com.upc.TuCine.security.domain.model.enumeration.Genders;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genders")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //For connection
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 10, nullable = false)
    private Genders name;
}

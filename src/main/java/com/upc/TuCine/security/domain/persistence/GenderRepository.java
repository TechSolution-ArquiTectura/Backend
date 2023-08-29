package com.upc.TuCine.security.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.enumeration.Genders;

public interface GenderRepository extends JpaRepository<Gender,Integer> {
    Optional<Gender> findByName(Genders name);
    boolean existsByName(Genders name);
}

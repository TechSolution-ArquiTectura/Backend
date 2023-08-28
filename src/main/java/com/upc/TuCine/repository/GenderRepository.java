package com.upc.TuCine.repository;

import com.upc.TuCine.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender,Integer> {
    boolean existsGenderByName(String name);
}

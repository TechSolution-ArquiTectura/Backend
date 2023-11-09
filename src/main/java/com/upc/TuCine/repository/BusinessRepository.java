package com.upc.TuCine.repository;

import com.upc.TuCine.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business,Integer> {
    boolean existsBusinessByName(String name);
    boolean existsBusinessByRuc(String ruc);
    Business findByUserId(Integer userId);
}

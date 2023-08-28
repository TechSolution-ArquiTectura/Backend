package com.upc.TuCine.repository;

import com.upc.TuCine.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTypeRepository extends JpaRepository<BusinessType,Integer> {
    boolean existsBusinessTypeByName(String name);
}

package com.upc.TuCine.repository;

import com.upc.TuCine.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    boolean existsByName(String name);
}

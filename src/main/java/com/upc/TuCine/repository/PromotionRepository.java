package com.upc.TuCine.repository;

import com.upc.TuCine.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    boolean existsPromotionByTitle(String title);
}

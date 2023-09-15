package com.upc.TuCine.repository;

import com.upc.TuCine.model.Promotion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findByBusinessId(Integer businessId);

    boolean existsPromotionByName(String name);
}

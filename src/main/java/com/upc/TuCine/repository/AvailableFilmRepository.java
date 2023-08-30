package com.upc.TuCine.repository;

import com.upc.TuCine.model.AvailableFilm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailableFilmRepository extends JpaRepository<AvailableFilm, Integer> {

    List<AvailableFilm> findAllByBusinessId(Integer business_id);
    List<AvailableFilm> findAllByFilmId(Integer film_id);
    List<AvailableFilm> findAllByPromotionId(Integer promotion_id);
}

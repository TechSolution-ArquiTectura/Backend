package com.upc.TuCine.repository;

import com.upc.TuCine.model.ContentRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRatingRepository extends JpaRepository<ContentRating,Integer> {

    boolean existsContentRatingByName(String name);
}

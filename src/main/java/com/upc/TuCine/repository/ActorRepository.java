package com.upc.TuCine.repository;

import com.upc.TuCine.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
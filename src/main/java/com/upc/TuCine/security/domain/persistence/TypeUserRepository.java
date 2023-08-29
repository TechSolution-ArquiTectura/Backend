package com.upc.TuCine.security.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upc.TuCine.security.domain.model.entity.TypeUser;
import com.upc.TuCine.security.domain.model.enumeration.TypeUsers;

public interface TypeUserRepository extends JpaRepository<TypeUser,Integer> {
    Optional<TypeUser> findByName(TypeUsers name);
    boolean existsByName(TypeUsers name);
}

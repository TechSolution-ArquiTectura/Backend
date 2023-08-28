package com.upc.TuCine.repository;

import com.upc.TuCine.model.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUserRepository extends JpaRepository<TypeUser,Integer> {
    boolean existsTypeUserByName(String name);

}

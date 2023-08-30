package com.upc.TuCine.repository;

import com.upc.TuCine.model.PaymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    List<PaymentMethod> findByUserId (Integer userId);

    Optional<PaymentMethod> findByIdAndUserId(Integer id, Integer userId);
}

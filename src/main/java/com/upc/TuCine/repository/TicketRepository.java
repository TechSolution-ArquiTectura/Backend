package com.upc.TuCine.repository;

import com.upc.TuCine.model.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUserId (Integer userId);
}

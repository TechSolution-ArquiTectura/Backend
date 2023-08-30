package com.upc.TuCine.service;

import com.upc.TuCine.dto.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> getAllTickets();
    List<TicketDto> getAllTicketsByUserId(Integer userId);

    TicketDto createTicket(TicketDto ticketDto);
    TicketDto updateTicket(Integer id, TicketDto ticketDto);
    TicketDto deleteTicket(Integer id);
}

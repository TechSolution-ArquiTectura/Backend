package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.TicketDto;
import com.upc.TuCine.mapping.TicketMapper;
import com.upc.TuCine.model.Ticket;
import com.upc.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.repository.TicketRepository;
import com.upc.TuCine.service.TicketService;
import com.upc.TuCine.shared.exception.ResourceNotFoundException;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.user.domain.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String ENTITY = "Employee";
    private Validator validator;
    private TicketMapper mapper;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private UserRepository userRepository;

    public TicketServiceImpl(TicketMapper ticketMapper, Validator validator) {
        this.mapper = ticketMapper;
        this.validator = validator;
    }

    @Override
    public List<TicketDto> getAllTickets() {

        return mapper.modelListToResource(ticketRepository.findAll());
    }

    @Override
    public List<TicketDto> getAllTicketsByUserId(Integer userId) {
        existsUserById(userId);
        return mapper.modelListToResource(ticketRepository.findByUserId(userId));
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = mapper.toModel(ticketDto);
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        existsUserById(ticket.getUser().getId());
        existsShowtimeById(ticket.getShowtime().getId());

        return mapper.toResource(ticketRepository.save(ticket));
    }

    @Override
    public TicketDto updateTicket(Integer id, TicketDto ticketDto) {
        Ticket ticket = mapper.toModel(ticketDto);
        Set<ConstraintViolation<Ticket>> violations = validator.validate(ticket);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        existsUserById(ticket.getUser().getId());
        existsShowtimeById(ticket.getShowtime().getId());

        Ticket resourceUpdated = ticketRepository.findById(id)
                .map(data -> ticketRepository.save(data.withNumberSeats(ticket.getNumberSeats())
                        .withPaymentToken(ticket.getPaymentToken())
                        .withTotalPrice(ticket.getTotalPrice())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));

        return mapper.toResource(resourceUpdated);
    }

    @Override
    public TicketDto deleteTicket(Integer id) {
        Ticket ticketToDelete = ticketRepository.findById(id).orElse(null);
        if (ticketToDelete == null) {
            throw new ResourceNotFoundException(ENTITY, id);
        }
        ticketRepository.delete(ticketToDelete);
        return mapper.toResource(ticketToDelete);
    }

    private void existsUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceValidationException("UserId not found");
        }
    }

    private void existsShowtimeById(Integer id) {
        if (!showtimeRepository.existsById(id)) {
            throw new ResourceValidationException("ShowtimeId not found");
        }
    }
}

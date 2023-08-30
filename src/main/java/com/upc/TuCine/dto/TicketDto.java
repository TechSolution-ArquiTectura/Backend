package com.upc.TuCine.dto;

import com.upc.TuCine.model.Showtime;
import com.upc.TuCine.user.domain.model.entity.User;

import lombok.Data;

@Data
public class TicketDto {
    private Integer id;
    private Integer numberSeats;
    private Float totalPrice;
    private String paymentToken;
    private User user;
    private Showtime showtime;
}

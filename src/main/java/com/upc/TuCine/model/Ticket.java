package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.user.domain.model.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Positive(message = "The number of seats must be greater than 0")
    @Column(name = "number_seats", nullable = false)
    private Integer numberSeats;

    @NotNull
    @Positive(message = "The total price must be greater than 0")
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    @NotNull
    @NotBlank
    @Size(max=60)
    @Column(name = "payment_token", length = 60, nullable = false)
    private String paymentToken;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_USER"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_SHOWTIME"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Showtime showtime;
}

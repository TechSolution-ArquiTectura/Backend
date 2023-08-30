package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

public class AvailableFilm {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_PERSON"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Business business;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_SHOWTIME"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Film film;

    @Column(name = "custom_notice")
    @Size(max = 80)
    private String customNotice;

    @Column(name = "is_available", nullable = false)
    @Size(max = 1)
    private char isAvailable;

    @Column(name = "promotion_id", nullable = false)
    private Promotion promotion;
}

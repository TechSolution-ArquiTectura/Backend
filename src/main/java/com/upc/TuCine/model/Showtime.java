package com.upc.TuCine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "available_film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SHOWTIME_FILM"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AvailableFilm availableFilm;

    @Column(name = "play_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate playDate;

    @Column(name = "playtime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime playtime;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

}

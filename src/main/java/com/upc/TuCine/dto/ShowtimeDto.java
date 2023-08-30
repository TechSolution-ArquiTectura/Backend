package com.upc.TuCine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upc.TuCine.model.AvailableFilm;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowtimeDto {
    private Integer id;
    private AvailableFilm availableFilm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate playDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime playtime;
    private Integer capacity;
    private Float unitPrice;
}

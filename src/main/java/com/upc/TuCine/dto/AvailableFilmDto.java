package com.upc.TuCine.dto;

import com.upc.TuCine.model.Business;
import com.upc.TuCine.model.Film;
import com.upc.TuCine.model.Promotion;
import lombok.Data;

@Data
public class AvailableFilmDto {
    private Integer id;
    private Business business;
    private Film film;
    private String customNotice;
    private Character isAvailable;
    private Promotion promotion;

}

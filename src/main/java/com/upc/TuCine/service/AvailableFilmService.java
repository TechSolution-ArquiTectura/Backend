package com.upc.TuCine.service;

import com.upc.TuCine.dto.AvailableFilmDto;

import java.util.List;

public interface AvailableFilmService {
    List<AvailableFilmDto> getAllAvailableFilms();
    AvailableFilmDto createAvailableFilm(AvailableFilmDto availableFilmDto);
    AvailableFilmDto updateAvailableFilm(Integer id, AvailableFilmDto availableFilmDto);
    AvailableFilmDto deleteAvailableFilm(Integer id);
}

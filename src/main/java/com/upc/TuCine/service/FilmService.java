package com.upc.TuCine.service;

import com.upc.TuCine.dto.*;
import com.upc.TuCine.model.Showtime;

import java.util.List;

public interface FilmService {

    List<FilmDto>getAllFilms();

    FilmDto getFilmById(Integer id);

    FilmDto createFilm(FilmDto filmDto);


    ContentRatingDto getContentRatingByFilmId(Integer id);

    List<CategoryDto>getAllCategoriesByFilmId(Integer id);

    List<ShowtimeDto>getAllShowtimesByFilmId(Integer id);

    List<ActorDto>getAllActorsByFilmId(Integer id);

    void addActorToFilm(Integer idFilm, Integer idActor);

    void addCategoryToFilm(Integer idFilm, Integer idCategory);


}

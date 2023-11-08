package com.upc.TuCine.repository;

import com.upc.TuCine.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    //Found all showtimes by film_id
    List<Showtime> findAllByAvailableFilmId(Integer available_film_id);
    //List<Showtime> findAllByBusiness_id(Integer business_id);

    @Query("SELECT s FROM Showtime s WHERE s.availableFilm.film.id = :filmId")
    List<Showtime> findShowtimesByFilmId(Integer filmId);
}

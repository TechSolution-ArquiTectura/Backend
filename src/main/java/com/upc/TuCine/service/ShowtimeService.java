package com.upc.TuCine.service;

import com.upc.TuCine.dto.ShowtimeDto;

import java.util.List;

public interface ShowtimeService {

    List<ShowtimeDto>getAllShowtimes();

    ShowtimeDto getShowtimeById(Integer id);

    ShowtimeDto createShowtime(ShowtimeDto showtimeDto);

    ShowtimeDto updateShowtime(Integer id, ShowtimeDto showtimeDto);

    ShowtimeDto deleteShowtime(Integer id);


}

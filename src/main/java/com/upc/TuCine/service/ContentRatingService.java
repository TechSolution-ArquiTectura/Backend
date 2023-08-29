package com.upc.TuCine.service;

import com.upc.TuCine.dto.ContentRatingDto;

import java.util.List;

public interface ContentRatingService {

    List<ContentRatingDto> getAllContentRatings();

    ContentRatingDto createContentRating(ContentRatingDto contentRatingDto);

    ContentRatingDto getContentRatingById(Integer id);


}

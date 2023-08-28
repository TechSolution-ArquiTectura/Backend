package com.upc.TuCine.service;

import com.upc.TuCine.dto.GenderDto;

import java.util.List;

public interface GenderService {

    List<GenderDto> getAllGenders();

    GenderDto createGender(GenderDto genderDto);

}

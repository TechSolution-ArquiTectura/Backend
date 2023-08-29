package com.upc.TuCine.security.domain.service;

import java.util.List;

import com.upc.TuCine.security.resource.GenderDto;

public interface GenderService {
    void seed();
    List<GenderDto> getAllGenders();
    
    // GenderDto createGender(GenderDto genderDto);
}

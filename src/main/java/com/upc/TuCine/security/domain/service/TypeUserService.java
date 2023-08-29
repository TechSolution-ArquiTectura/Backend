package com.upc.TuCine.security.domain.service;

import java.util.List;

import com.upc.TuCine.security.resource.TypeUserDto;

public interface TypeUserService {
    void seed();
    List<TypeUserDto> getAllTypeUsers();
    // TypeUserDto createTypeUser(TypeUserDto typeUserDto);
}

package com.upc.TuCine.service;

import com.upc.TuCine.dto.TypeUserDto;

import java.util.List;

public interface TypeUserService {

    List<TypeUserDto> getAllTypeUsers();

    TypeUserDto createTypeUser(TypeUserDto typeUserDto);


}

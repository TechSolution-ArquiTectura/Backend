package com.upc.TuCine.user.domain.service;

import com.upc.TuCine.security.domain.service.communication.AuthenticateRequest;
import com.upc.TuCine.security.domain.service.communication.RegisterRequest;
import com.upc.TuCine.security.resource.TypeUserDto;
import com.upc.TuCine.user.resource.UserDto;

import org.springframework.http.ResponseEntity;


import java.util.List;

public interface UserService {
    //get
    List<UserDto> getAllUsers();
    UserDto getById(Integer userId);

    TypeUserDto getTypeUserById(Integer id);

    ResponseEntity<?> register(RegisterRequest request);
}

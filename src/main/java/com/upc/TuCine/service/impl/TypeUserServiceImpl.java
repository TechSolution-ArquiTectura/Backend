package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.TypeUserDto;
import com.upc.TuCine.model.Ticket;
import com.upc.TuCine.model.TypeUser;
import com.upc.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.service.TypeUserService;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TypeUserServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private TypeUserDto EntityToDto(TypeUser typeUser){
        return modelMapper.map(typeUser, TypeUserDto.class);
    }

    private TypeUser DtoToEntity(TypeUserDto typeUserDto){
        return modelMapper.map(typeUserDto, TypeUser.class);
    }
    @Override
    public List<TypeUserDto> getAllTypeUsers() {
        List<TypeUser> typeUsers = typeUserRepository.findAll();
        return typeUsers.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeUserDto createTypeUser(TypeUserDto typeUserDto) {
        validateTypeUser(typeUserDto);
        existsTypeUserByName(typeUserDto.getName());
        TypeUser typeUser = DtoToEntity(typeUserDto);
        TypeUser createdTypeUser = typeUserRepository.save(typeUser);
        return EntityToDto(createdTypeUser);
    }
    void validateTypeUser(TypeUserDto typeUser) {
        if (typeUser.getName() == null || typeUser.getName().isEmpty()) {
            throw new ResourceValidationException("El nombre del tipo de usuario no puede estar vacío");
        }
    }

    void existsTypeUserByName(String name) {
        if (typeUserRepository.existsTypeUserByName(name)) {
            throw new ResourceValidationException("El tipo de usuario ya existe con este nombre");
        }
    }
}

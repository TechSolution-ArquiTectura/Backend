package com.upc.TuCine.security.service;

import com.upc.TuCine.security.domain.model.entity.TypeUser;
import com.upc.TuCine.security.domain.model.enumeration.TypeUsers;
import com.upc.TuCine.security.domain.persistence.TypeUserRepository;
import com.upc.TuCine.security.domain.service.TypeUserService;
import com.upc.TuCine.security.mapping.TypeUserMapper;
import com.upc.TuCine.security.resource.TypeUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository typeUserRepository;
    private TypeUserMapper mapper;

    private static String[] DEFAULT_TYPE_USERS = { "CINEPHILE", "BUSINESS", "ADMIN" };

    public TypeUserServiceImpl(TypeUserMapper typeUserMapper) {
        this.mapper = typeUserMapper;
    }

    @Override
    public List<TypeUserDto> getAllTypeUsers() {
        return mapper.toResourceList(typeUserRepository.findAll());
    }

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_TYPE_USERS).forEach(name -> {
            TypeUsers typeUserName = TypeUsers.valueOf(name);
            if (!typeUserRepository.existsByName(typeUserName)) {
                typeUserRepository.save((new TypeUser()).withName(typeUserName));
            }
        });
    }

    // @Override
    // public TypeUserDto createTypeUser(TypeUserDto typeUserDto) {
    // validateTypeUser(typeUserDto);
    // existsTypeUserByName(typeUserDto.getName());
    // TypeUser typeUser = DtoToEntity(typeUserDto);
    // TypeUser createdTypeUser = typeUserRepository.save(typeUser);
    // return EntityToDto(createdTypeUser);
    // }
    // void validateTypeUser(TypeUserDto typeUser) {
    // if (typeUser.getName() == null || typeUser.getName().isEmpty()) {
    // throw new ResourceValidationException("El nombre del tipo de usuario no puede
    // estar vacío");
    // }
    // }

    // void existsTypeUserByName(String name) {
    // if (typeUserRepository.existsTypeUserByName(name)) {
    // throw new ResourceValidationException("El tipo de usuario ya existe con este
    // nombre");
    // }
    // }
}

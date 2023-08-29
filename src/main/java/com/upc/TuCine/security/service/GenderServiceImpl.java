package com.upc.TuCine.security.service;

import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.enumeration.Genders;
import com.upc.TuCine.security.domain.persistence.GenderRepository;
import com.upc.TuCine.security.domain.service.GenderService;
import com.upc.TuCine.security.mapping.GenderMapper;
import com.upc.TuCine.security.resource.GenderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Arrays;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;
    private GenderMapper mapper;

    private static String[] DEFAULT_GENDERS = { "MALE", "FEMALE", "OTHER" };

    GenderServiceImpl(GenderMapper genderMapper) {
        this.mapper = genderMapper;
    }

    @Override
    public List<GenderDto> getAllGenders() {
        return mapper.toResourceList(genderRepository.findAll());
    }

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_GENDERS).forEach(name -> {
            Genders genderName = Genders.valueOf(name);
            if (!genderRepository.existsByName(genderName)) {
                genderRepository.save((new Gender()).withName(genderName));
            }
        });
    }

    // @Override
    // public GenderDto createGender(GenderDto genderDto) {
    //     genderValidate(genderDto);
    //     existsGenderByName(genderDto.getName());

    //     Gender gender = DtoToEntity(genderDto);
    //     return EntityToDto(genderRepository.save(gender));
    // }

    // void genderValidate(GenderDto gender) {
    //     if (gender.getName() == null || gender.getName().isEmpty()) {
    //         throw new ResourceValidationException("El nombre es obligatorio");
    //     }
    // }

    // void existsGenderByName(String name) {
    //     if (genderRepository.existsGenderByName(name)) {
    //         throw new ResourceValidationException("No se puede registrar el genero, ya existe uno con ese nombre");
    //     }
    // }
}

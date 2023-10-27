package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.Business.BusinessDto;
import com.upc.TuCine.dto.Business.RegisterBusiness;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.model.*;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.BusinessTypeRepository;
import com.upc.TuCine.user.domain.model.entity.User;
import com.upc.TuCine.user.domain.persistence.UserRepository;
import java.util.Collections;
import com.upc.TuCine.service.BusinessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    BusinessServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    public BusinessDto entitytoDto(Business business){
        return modelMapper.map(business, BusinessDto.class);
    }

    public Business dtoToEntity(BusinessDto businessDto){
        return modelMapper.map(businessDto, Business.class);
    }

    public Business registerToEntity(RegisterBusiness registerBusiness){
        return modelMapper.map(registerBusiness, Business.class);
    }

    @Override
    public Business createBusiness(RegisterBusiness newBusiness) {

        validateBusiness(newBusiness);
        existsByBusinessName(newBusiness.getName());
        existsByBusinessRuc(newBusiness.getRuc());


        User owner = userRepository.findById(newBusiness.getUser().getId()).orElse(null);
        newBusiness.setUser(owner);
        newBusiness.setRating((float) 0);
        newBusiness.setCommentsCount(0);
        
        Business business = registerToEntity(newBusiness);
        return businessRepository.save(business);
    }

    @Override
    public List<BusinessDto> getAllBusiness() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(this::entitytoDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessDto getBusinessById(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        return entitytoDto(business);
    }


    @Override
    public List<BusinessTypeDto> getAllBusinessTypesByBusinessId(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return Collections.emptyList();
        }
        return business.getBusinessTypes().stream()
                .map(businessType -> modelMapper.map(businessType, BusinessTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addBusinessTypeToBusiness(Integer idBusiness, Integer idBusinessType) {
        Business business = businessRepository.findById(idBusiness).orElseThrow(() -> new ResourceValidationException("El negocio no existe" ));
        BusinessType businessType = businessTypeRepository.findById(idBusinessType).orElseThrow(() -> new ResourceValidationException("El tipo de negocio no existe" ));

        business.getBusinessTypes().add(businessType);
        businessRepository.save(business);
    }

    @Override
    public BusinessDto updateBusiness(Integer id, BusinessDto newBusiness) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        newBusiness.setId(id);
        business = dtoToEntity(newBusiness);

        return entitytoDto(businessRepository.save(business));

    }

    public void validateBusiness(RegisterBusiness business) {
        if (business.getName() == null || business.getName().isEmpty()) {
            throw new ResourceValidationException("El nombre de negocio es obligatorio");
        }
        if(business.getSocialReason()==null || business.getSocialReason().isEmpty()){
            throw new ResourceValidationException("La razón social es obligatoria");
        }
        if (business.getRuc() == null || business.getRuc().isEmpty()) {
            throw new ResourceValidationException("El RUC es obligatorio");
        }
        if(business.getDescription()==null || business.getDescription().isEmpty()){
            throw new ResourceValidationException("La descripción es obligatoria");
        }
        if (business.getAddress() == null || business.getAddress().isEmpty()) {
            throw new ResourceValidationException("La dirección es obligatoria");
        }

        //FK
        if(business.getUser()==null){
            throw new ResourceValidationException("El dueño es obligatorio");
        }
    }

    public void existsByBusinessName(String businessName) {
        if (businessRepository.existsBusinessByName(businessName)) {
            throw new ResourceValidationException("El nombre de negocio ya existe");
        }
    }
    public void existsByBusinessRuc(String businessRuc) {
        if (businessRepository.existsBusinessByRuc(businessRuc)) {
            throw new ResourceValidationException("Un Business con ese RUC ya existe");
        }
    }



}

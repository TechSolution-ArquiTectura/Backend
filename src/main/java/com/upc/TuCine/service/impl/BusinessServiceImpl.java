package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.BusinessDto;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.model.*;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.BusinessTypeRepository;
import com.upc.TuCine.repository.ShowtimeRepository;
import com.upc.TuCine.service.BusinessService;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.user.domain.model.entity.User;
import com.upc.TuCine.user.domain.persistence.UserRepository;

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
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ModelMapper modelMapper;

    BusinessServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    public BusinessDto EntityToDto(Business business){
        return modelMapper.map(business, BusinessDto.class);
    }

    public Business DtoToEntity(BusinessDto businessDto){
        return modelMapper.map(businessDto, Business.class);
    }

    public BusinessTypeDto convertBusinessTypeToDto(BusinessType businessType){
        return modelMapper.map(businessType, BusinessTypeDto.class);
    }

    @Override
    public BusinessDto createBusiness(BusinessDto businessDto) {

        validateBusiness(businessDto);
        existsByBusinessName(businessDto.getName());
        existsByBusinessRuc(businessDto.getRuc());
        existsByBusinessEmail(businessDto.getEmail());


        User user = userRepository.findById(businessDto.getUser().getId()).orElse(null);
        businessDto.setUser(user);

        BusinessType businessType = businessTypeRepository.findById(businessDto.getBusinessType().getId()).orElse(null);
        businessDto.setBusinessType(businessType);

        Business business = DtoToEntity(businessDto);
        return EntityToDto(businessRepository.save(business));
    }

    @Override
    public List<BusinessDto> getAllBusiness() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessDto getBusinessById(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        return EntityToDto(business);
    }


    @Override
    public BusinessTypeDto getBusinessTypeByBusinessId(Integer id) {
        Business business = businessRepository.getById(id);
        if (business == null) {
            return null;
        }
        BusinessType businessType = business.getBusinessType();
        return convertBusinessTypeToDto(businessType);
    }

    @Override
    public List<ShowtimeDto> getAllShowtimesByBusinessId(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        List<ShowtimeDto> showtimes = showtimeRepository.findAllByBusiness_id(business.getId()).stream()
                .map(showtime -> modelMapper.map(showtime, ShowtimeDto.class))
                .collect(Collectors.toList());
        return showtimes;
    }


    public void validateBusiness(BusinessDto business) {
        if (business.getName() == null || business.getName().isEmpty()) {
            throw new ResourceValidationException("El nombre de negocio es obligatorio");
        }
        if(business.getSocialReason()==null || business.getSocialReason().isEmpty()){
            throw new ResourceValidationException("La razón social es obligatoria");
        }
        if (business.getRuc() == null || business.getRuc().isEmpty()) {
            throw new ResourceValidationException("El RUC es obligatorio");
        }
        if (business.getEmail() == null || business.getEmail().isEmpty()) {
            throw new ResourceValidationException("El correo es obligatorio");
        }
        if (business.getAddress() == null || business.getAddress().isEmpty()) {
            throw new ResourceValidationException("La dirección es obligatoria");
        }
        if (business.getPhone() == null || business.getPhone().isEmpty()) {
            throw new ResourceValidationException("El teléfono es obligatorio");
        }
        if(business.getImageLogo()==null || business.getImageLogo().isEmpty()){
            throw new ResourceValidationException("La imagen es obligatoria");
        }
        if(business.getImageBanner()==null || business.getImageBanner().isEmpty()){
            throw new ResourceValidationException("La imagen es obligatoria");
        }
        if(business.getDescription()==null || business.getDescription().isEmpty()){
            throw new ResourceValidationException("La descripción es obligatoria");
        }
        if(business.getDateAttention()==null || business.getDateAttention().isEmpty()){
            throw new ResourceValidationException("La fecha de atención es obligatoria");
        }
        if(business.getReferenceAddress()==null || business.getReferenceAddress().isEmpty()){
            throw new ResourceValidationException("La referencia de la dirección es obligatoria");
        }
        if(business.getBusinessType()==null){
            throw new ResourceValidationException("El tipo de negocio es obligatorio");
        }
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
    public void existsByBusinessEmail(String businessEmail) {
        if (businessRepository.existsBusinessByEmail(businessEmail)) {
            throw new ResourceValidationException("Un Business con ese correo ya existe");
        }
    }
}

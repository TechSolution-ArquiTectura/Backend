package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.ActorDto;
import com.upc.TuCine.dto.BusinessDto;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.model.*;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.BusinessTypeRepository;
import com.upc.TuCine.user.domain.model.entity.User;
import com.upc.TuCine.user.domain.persistence.UserRepository;
import com.upc.TuCine.repository.ShowtimeRepository;
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

        User owner = userRepository.findById(businessDto.getUser().getId()).orElse(null);
        businessDto.setUser(owner);

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
    public List<BusinessTypeDto> getAllBusinessTypesByBusinessId(Integer id) {
        Business business = businessRepository.findById(id).orElse(null);
        if (business == null) {
            return null;
        }
        List<BusinessTypeDto> businessTypes =business.getBusinessTypes().stream()
                .map(businessType -> modelMapper.map(businessType, BusinessTypeDto.class))
                .collect(Collectors.toList());
        return businessTypes;

    }

    @Override
    public void addBusinessTypeToBusiness(Integer idBusiness, Integer idBusinessType) {
        Business business = businessRepository.findById(idBusiness).orElseThrow(() -> new ResourceValidationException("El negocio no existe" ));
        BusinessType businessType = businessTypeRepository.findById(idBusinessType).orElseThrow(() -> new ResourceValidationException("El tipo de negocio no existe" ));

        business.getBusinessTypes().add(businessType);
        businessRepository.save(business);
    }

    // ESTO TIENE QUE VER CON SHOWTIME OJO
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
        if (business.getPhone() == null || business.getPhone().isEmpty()) {
            throw new ResourceValidationException("El teléfono es obligatorio");
        }
        if(business.getLogoSrc()==null || business.getLogoSrc().isEmpty()){
            throw new ResourceValidationException("La imagen es obligatoria");
        }
        if(business.getBannerSrc()==null || business.getBannerSrc().isEmpty()){
            throw new ResourceValidationException("La imagen es obligatoria");
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

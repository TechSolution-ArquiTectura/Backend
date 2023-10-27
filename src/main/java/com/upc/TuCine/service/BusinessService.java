package com.upc.TuCine.service;

import com.upc.TuCine.dto.Business.BusinessDto;
import com.upc.TuCine.dto.Business.RegisterBusiness;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.model.Business;

import java.util.List;

public interface BusinessService {

    Business createBusiness(RegisterBusiness newBusiness);

    List<BusinessDto> getAllBusiness();

    BusinessDto getBusinessById(Integer id);

    //Get all business types from a business
    List<BusinessTypeDto> getAllBusinessTypesByBusinessId(Integer id);

    void addBusinessTypeToBusiness(Integer idBusiness, Integer idBusinessType);

    BusinessDto updateBusiness(Integer id, BusinessDto newBusiness);
}

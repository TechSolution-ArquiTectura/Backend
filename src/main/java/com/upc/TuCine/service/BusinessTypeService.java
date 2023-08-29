package com.upc.TuCine.service;

import com.upc.TuCine.dto.BusinessTypeDto;

import java.util.List;

public interface BusinessTypeService {

    List<BusinessTypeDto>getAllBusinessTypes();

    BusinessTypeDto getBusinessTypeById(Integer id);

    BusinessTypeDto createBusinessType(BusinessTypeDto businessTypeDto);

    boolean existsBusinessTypeByName(String name);

}

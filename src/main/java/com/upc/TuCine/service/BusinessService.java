package com.upc.TuCine.service;

import com.upc.TuCine.dto.BusinessDto;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.dto.ShowtimeDto;

import java.util.List;

public interface BusinessService {

    BusinessDto createBusiness(BusinessDto businessDto);

    List<BusinessDto> getAllBusiness();

    BusinessDto getBusinessById(Integer id);

    BusinessTypeDto getBusinessTypeByBusinessId(Integer id);

    List<ShowtimeDto>getAllShowtimesByBusinessId(Integer id);


}

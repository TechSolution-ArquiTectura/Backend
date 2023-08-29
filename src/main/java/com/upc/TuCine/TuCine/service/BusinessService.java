package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.BusinessDto;
import com.upc.TuCine.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.model.Business;

import java.util.List;

public interface BusinessService {

    BusinessDto createBusiness(BusinessDto businessDto);

    List<BusinessDto> getAllBusiness();

    BusinessDto getBusinessById(Integer id);

    //Get all business types from a business
    List<BusinessTypeDto> getAllBusinessTypesByBusinessId(Integer id);



    // ESTO TIENE QUE VER CON SHOWTIME OJO
    List<ShowtimeDto>getAllShowtimesByBusinessId(Integer id);


}

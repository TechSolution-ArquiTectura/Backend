package com.upc.TuCine.service;

import com.upc.TuCine.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> getAllOwners();

    OwnerDto createOwner(OwnerDto ownerDto);
}

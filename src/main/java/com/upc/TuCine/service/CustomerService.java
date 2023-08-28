package com.upc.TuCine.service;

import com.upc.TuCine.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);
}

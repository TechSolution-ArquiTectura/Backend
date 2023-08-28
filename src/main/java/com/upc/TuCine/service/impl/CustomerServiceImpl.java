package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.CustomerDto;
import com.upc.TuCine.model.Category;
import com.upc.TuCine.model.Customer;
import com.upc.TuCine.model.Person;
import com.upc.TuCine.repository.CustomerRepository;
import com.upc.TuCine.repository.PersonRepository;
import com.upc.TuCine.service.CustomerService;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    CustomerServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private CustomerDto EntityToDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer DtoToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        validateCustomer(customerDto);

        Person person = personRepository.findById(customerDto.getPerson().getId()).orElse(null);
        customerDto.setPerson(person);

        Customer customer = DtoToEntity(customerDto);
        return EntityToDto(customerRepository.save(customer));
    }

    private void validateCustomer(CustomerDto customer) {
        if (customer.getPerson() == null) {
            throw new ResourceValidationException("La persona es obligatoria");
        }
    }
}

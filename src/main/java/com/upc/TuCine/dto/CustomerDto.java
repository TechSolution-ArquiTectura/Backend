package com.upc.TuCine.dto;

import com.upc.TuCine.model.Person;
import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private Person person;
}

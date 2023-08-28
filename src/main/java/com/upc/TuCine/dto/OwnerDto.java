package com.upc.TuCine.dto;

import com.upc.TuCine.model.Person;
import lombok.Data;

@Data
public class OwnerDto {
    private Integer id;
    private String bankAccount;
    private Person person;
}

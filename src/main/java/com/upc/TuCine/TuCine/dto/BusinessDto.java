package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.BusinessType;
import com.upc.TuCine.TuCine.model.Owner;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BusinessDto {
    private Integer id;
    private String name;
    private String socialReason;
    private String ruc;
    private String phone;
    private String logoSrc;
    private String bannerSrc;
    private String description;
    private String dateAttention;
    private String address;
    private LocalDate startTime;
    private LocalDate endTime;


    private Owner owner;
    private BusinessType businessType;
}

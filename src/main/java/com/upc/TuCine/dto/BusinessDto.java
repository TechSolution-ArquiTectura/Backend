package com.upc.TuCine.dto;

import com.upc.TuCine.model.BusinessType;

import com.upc.TuCine.user.domain.model.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
    private String address;
    private LocalDate startTime;
    private LocalDate endTime;

    private User user;
    private List<BusinessType> businessTypes;
}

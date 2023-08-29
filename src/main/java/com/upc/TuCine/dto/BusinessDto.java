package com.upc.TuCine.dto;

import com.upc.TuCine.model.BusinessType;
import com.upc.TuCine.user.domain.model.entity.User;

import lombok.Data;

@Data
public class BusinessDto {
    private Integer id;
    private String name;
    private String socialReason;
    private String ruc;
    private String phone;
    private String email;
    private String imageLogo;
    private String imageBanner;
    private String description;
    private String dateAttention;
    private String address;
    private String referenceAddress;
    private User user;
    private BusinessType businessType;
}

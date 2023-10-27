package com.upc.TuCine.dto.Business;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.TuCine.model.BusinessType;
import com.upc.TuCine.user.domain.model.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RegisterBusiness {

    @NotBlank
    private String name;
    @NotBlank
    private String socialReason;
    @NotBlank
    private String ruc;
    @NotBlank
    private String description;
    @NotBlank
    private String address;
    private Float rating;
    private Integer commentsCount;


    private User user;
    private List<BusinessType> businessTypes;
}

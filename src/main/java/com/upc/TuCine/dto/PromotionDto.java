package com.upc.TuCine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upc.TuCine.model.Business;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class PromotionDto {
    private Integer id;
    private Business business;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate initDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private Float discountPercentage;
    private String imageSrc;
}

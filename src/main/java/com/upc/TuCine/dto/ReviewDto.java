package com.upc.TuCine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upc.TuCine.model.Business;
import com.upc.TuCine.user.domain.model.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ReviewDto {
    private Integer id;
    private String comment;
    private Integer rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate updatedAt;
    private User user;
    private Business business;

}

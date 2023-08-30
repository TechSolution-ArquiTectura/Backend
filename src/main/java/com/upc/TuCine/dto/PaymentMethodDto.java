package com.upc.TuCine.dto;

import com.upc.TuCine.user.domain.model.entity.User;
import lombok.Data;


@Data
public class PaymentMethodDto {
    private Integer id;
    private String paymentToken;
    private User user;
}

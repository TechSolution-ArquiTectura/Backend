package com.upc.TuCine.service;


import com.upc.TuCine.dto.PaymentMethodDto;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodDto> getAllPaymentMethods();
    List<PaymentMethodDto> getAllPaymentMethodsByUserId(Integer userId);
    PaymentMethodDto createPaymentMethods(PaymentMethodDto paymentMethodDto);
    PaymentMethodDto updatePaymentMethods(Integer id, PaymentMethodDto paymentMethodDto);
    PaymentMethodDto deletePaymentMethods(Integer id);
    PaymentMethodDto deletePaymentMethodByIdAndUserId(Integer id, Integer userId);
}



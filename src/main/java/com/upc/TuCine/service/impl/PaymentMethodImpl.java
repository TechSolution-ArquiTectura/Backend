package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.PaymentMethodDto;
import com.upc.TuCine.mapping.PaymentMethodMapper;
import com.upc.TuCine.model.PaymentMethod;
import com.upc.TuCine.repository.PaymentMethodRepository;
import com.upc.TuCine.service.PaymentMethodService;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.user.domain.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.Validator;
import java.util.List;

@Service
public class PaymentMethodImpl implements PaymentMethodService {

    private Validator validator;
    private PaymentMethodMapper mapper;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserRepository userRepository;

    public PaymentMethodImpl(PaymentMethodMapper paymentMethodMapper, Validator validator) {
        this.mapper = paymentMethodMapper;
        this.validator = validator;
    }

    @Override
    public List<PaymentMethodDto> getAllPaymentMethods() {
        return mapper.modelListToResource(paymentMethodRepository.findAll());
    }

    @Override
    public List<PaymentMethodDto> getAllPaymentMethodsByUserId(Integer userId) {
        existsUserById(userId);
        return mapper.modelListToResource(paymentMethodRepository.findByUserId(userId));
    }




    @Override
    public PaymentMethodDto createPaymentMethods(PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = mapper.toModel(paymentMethodDto);
        existsUserById(paymentMethod.getUser().getId());
        return mapper.toResource(paymentMethodRepository.save(paymentMethod));
    }

    @Override
    public PaymentMethodDto updatePaymentMethods(Integer id, PaymentMethodDto paymentMethodDto) {
        return null;
    }


    @Override
    public PaymentMethodDto deletePaymentMethods(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(() -> new ResourceValidationException("PaymentMethodsId not found"));
        paymentMethodRepository.delete(paymentMethod);
        return mapper.toResource(paymentMethod);
    }

    @Override
    public PaymentMethodDto deletePaymentMethodByIdAndUserId(Integer id, Integer userId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResourceValidationException("PaymentMethodsId not found"));
        paymentMethodRepository.delete(paymentMethod);
        return mapper.toResource(paymentMethod);
    }

    private void existsUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceValidationException("UserId not found");
        }
    }
}

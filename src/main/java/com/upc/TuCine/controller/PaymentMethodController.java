package com.upc.TuCine.controller;


import com.upc.TuCine.dto.PaymentMethodDto;
import com.upc.TuCine.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/paymentMethods
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/paymentMethods")
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods() {
        return new ResponseEntity<>(paymentMethodService.getAllPaymentMethods(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/paymentMethods/user/1
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/paymentMethods/user/{userId}")
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethodsByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(paymentMethodService.getAllPaymentMethodsByUserId(userId), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/paymentMethods
    //Method: POST
    @Transactional
    @PostMapping("/paymentMethods")
    public ResponseEntity<PaymentMethodDto> createPaymentMethods(@RequestBody PaymentMethodDto paymentMethodDto){
        PaymentMethodDto createdPaymentMethodDto= paymentMethodService.createPaymentMethods(paymentMethodDto);
        return new ResponseEntity<>(createdPaymentMethodDto, HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/paymentMethods/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/paymentMethods/{id}")
    public ResponseEntity<PaymentMethodDto> deletePaymentMethods(@PathVariable Integer id){
        PaymentMethodDto deletedPaymentMethodDto = paymentMethodService.deletePaymentMethods(id);
        if (deletedPaymentMethodDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedPaymentMethodDto,HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/paymentMethods/1/user/1
    //Method: DELETE
    @Transactional
    @DeleteMapping("/paymentMethods/{id}/user/{userId}")
    public ResponseEntity<PaymentMethodDto> deletePaymentMethodByIdAndUserId(@PathVariable Integer id, @PathVariable Integer userId){
        PaymentMethodDto deletedPaymentMethodDto = paymentMethodService.deletePaymentMethodByIdAndUserId(id, userId);
        if (deletedPaymentMethodDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedPaymentMethodDto,HttpStatus.OK);
    }

}

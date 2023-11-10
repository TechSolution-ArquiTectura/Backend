package com.upc.TuCine.controller;

import com.upc.TuCine.dto.Business.BusinessDto;
import com.upc.TuCine.dto.Business.RegisterBusiness;
import com.upc.TuCine.dto.BusinessTypeDto;
import com.upc.TuCine.model.Business;
import com.upc.TuCine.service.BusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses")
    public ResponseEntity<List<BusinessDto>> getAllBusinesses() {
        return new ResponseEntity<>(businessService.getAllBusiness(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}")
    public ResponseEntity<BusinessDto> getBusinessById(@PathVariable(value = "id") Integer id) {
        BusinessDto businessDto = businessService.getBusinessById(id);
        if (businessDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/business/{userId}")
    public ResponseEntity<BusinessDto> getBusinessByUserId(@PathVariable(value = "userId") Integer userId) {
        return new ResponseEntity<>(businessService.getBusinessByUserId(userId), HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/business/{name}")
    public boolean existsBusinessByName(@PathVariable(value = "name") String name) {
        return businessService.existsBusinessByName(name);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}/businessTypes/{id}
    //Method: POST
    @Transactional
    @PostMapping("/businesses/{businessId}/businessTypes/{businessTypesId}")
    public ResponseEntity<String> addBusinessTypeToBusiness(@PathVariable("businessId") Integer businessId, @PathVariable("businessTypesId") Integer businessTypesId) {
        businessService.addBusinessTypeToBusiness(businessId, businessTypesId);
        return new ResponseEntity<>("Business Type added to Business", HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}/businessTypes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/businesses/{id}/businessTypes")
    public ResponseEntity<List<BusinessTypeDto>> getAllBusinessTypesByBusinessId(@PathVariable("id") Integer id) {
        List<BusinessTypeDto> businessTypeDtoList = businessService.getAllBusinessTypesByBusinessId(id);
        if (businessTypeDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el business
        }
        return new ResponseEntity<>(businessTypeDtoList, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses
    //Method: POST
    @Transactional
    @PostMapping("/businesses")
    public ResponseEntity<Business> createBusiness(@RequestBody RegisterBusiness newBusiness){
        return new ResponseEntity<>(businessService.createBusiness(newBusiness), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/businesses/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/businesses/{id}")
    public ResponseEntity<BusinessDto> updateBusiness(@PathVariable(value = "id") Integer id, @RequestBody BusinessDto newBusiness){
        BusinessDto businessDto = businessService.updateBusiness(id, newBusiness);
        if (businessDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(businessDto, HttpStatus.OK);
    }
}

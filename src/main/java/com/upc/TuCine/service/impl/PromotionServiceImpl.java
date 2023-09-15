package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.PromotionDto;
import com.upc.TuCine.model.Business;
import com.upc.TuCine.model.Promotion;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.PromotionRepository;
import com.upc.TuCine.service.PromotionService;
import com.upc.TuCine.shared.exception.ResourceNotFoundException;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper modelMapper;

    PromotionServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private PromotionDto EntityToDto(Promotion promotion) {
        return modelMapper.map(promotion, PromotionDto.class);
    }

    private Promotion DtoToEntity(PromotionDto promotionDto) {
        return modelMapper.map(promotionDto, Promotion.class);
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDto getPromotionById(Integer id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promotion", id));
        return EntityToDto(promotion);
    }

    @Override
    public List<PromotionDto> getPromotionsByBusinessId(Integer businessId) {
        List<Promotion> promotions = promotionRepository.findByBusinessId(businessId);
        return promotions.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {

        validatePromotion(promotionDto);
        existsPromotionByName(promotionDto.getName());

        Business business;
        try {
            business = businessRepository.findById(promotionDto.getBusiness().getId()).orElse(null);
        } catch (Exception e) {
            business = null;
        }
        promotionDto.setBusiness(business);

        Promotion promotion = DtoToEntity(promotionDto);
        return EntityToDto(promotionRepository.save(promotion));
    }

    @Override
    public PromotionDto updatePromotion(Integer id, PromotionDto promotionDto) {
        Promotion promotionToUpdate = promotionRepository.findById(id).orElse(null);
        if (promotionToUpdate == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        validatePromotion(promotionDto);

        // Actualizar los campos de la promoción existente
        promotionToUpdate.setName(promotionDto.getName());
        promotionToUpdate.setDescription(promotionDto.getDescription());
        promotionToUpdate.setInitDate(promotionDto.getInitDate());
        promotionToUpdate.setEndDate(promotionDto.getEndDate());
        promotionToUpdate.setDiscountPercentage(promotionDto.getDiscountPercentage());
        promotionToUpdate.setImageSrc(promotionDto.getImageSrc());

        // Guardar la promoción actualizada en el repositorio
        Promotion updatedPromotion = promotionRepository.save(promotionToUpdate);

        return EntityToDto(updatedPromotion);
    }

    @Override
    public PromotionDto deletePromotion(Integer id) {
        Promotion promotionToDelete = promotionRepository.findById(id).orElse(null);
        if (promotionToDelete == null) {
            return null; // O lanzar una excepción si lo prefieres
        }
        promotionRepository.delete(promotionToDelete);
        return EntityToDto(promotionToDelete);
    }

    private void validatePromotion(PromotionDto promotion) {
        if (promotion.getName() == null || promotion.getName().isEmpty()) {
            throw new ResourceValidationException("El nombre no puede ser nulo o estar vacío");
        }
        if (promotion.getDescription() == null || promotion.getDescription().isEmpty()) {
            throw new ResourceValidationException("La descripción no puede ser nula o estar vacía");
        }
        if (promotion.getInitDate() == null) {
            throw new ResourceValidationException("La fecha de inicio no puede ser nula");
        }
        if (promotion.getEndDate() == null) {
            throw new ResourceValidationException("La fecha de fin no puede ser nula");
        }
        if (promotion.getInitDate().isAfter(promotion.getEndDate())) {
            throw new ResourceValidationException("La fecha de inicio no puede ser mayor a la fecha de fin");
        }
        if (promotion.getDiscountPercentage() == null) {
            throw new ResourceValidationException("El porcentaje de descuento no puede ser nulo");
        }
        if (promotion.getBusiness() == null) {
            throw new ResourceValidationException("El negocio no puede ser nulo");
        }
    }

    public void existsPromotionByName(String name) {
        if (promotionRepository.existsPromotionByName(name)) {
            throw new ResourceValidationException("Ya existe una promoción con el nombre " + name);
        }
    }
}

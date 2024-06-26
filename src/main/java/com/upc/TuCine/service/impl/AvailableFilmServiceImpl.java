package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.AvailableFilmDto;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.model.*;
import com.upc.TuCine.repository.AvailableFilmRepository;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.FilmRepository;
import com.upc.TuCine.repository.PromotionRepository;
import com.upc.TuCine.service.AvailableFilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableFilmServiceImpl implements AvailableFilmService {
    @Autowired
    private AvailableFilmRepository availableFilmRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ModelMapper modelMapper;

    AvailableFilmServiceImpl() { this.modelMapper = new ModelMapper(); }

    private AvailableFilmDto EntityToDto(AvailableFilm availableFilm) {
        return modelMapper.map(availableFilm, AvailableFilmDto.class);
    }

    private AvailableFilm DtoToEntity(AvailableFilmDto availableFilmDto) {
        return modelMapper.map(availableFilmDto, AvailableFilm.class);
    }

    @Override
    public List<AvailableFilmDto> getAllAvailableFilms() {
        List<AvailableFilm> availableFilms = availableFilmRepository.findAll();
        return availableFilms.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailableFilmDto> getAllAvailableFilmsByBusinessId(Integer id) {
        List<AvailableFilm> availableFilms = availableFilmRepository.findAllByBusinessId(id);
        return availableFilms.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AvailableFilmDto createAvailableFilm(AvailableFilmDto availableFilmDto) {

        validateAvailableFilm(availableFilmDto);
        existsBusinessById(availableFilmDto.getBusiness().getId());
        existsFilmById(availableFilmDto.getFilm().getId());
        //existsPromotionById(availableFilmDto.getPromotion().getId());

        Business business = businessRepository.findById(availableFilmDto.getBusiness().getId()).orElse(null);
        availableFilmDto.setBusiness(business);

        Film film = filmRepository.findById(availableFilmDto.getFilm().getId()).orElse(null);
        availableFilmDto.setFilm(film);

        Promotion promotion;
        try {
            promotion = promotionRepository.findById(availableFilmDto.getPromotion().getId()).orElse(null);
        } catch (Exception e) {
            promotion = null;
        }

        availableFilmDto.setPromotion(promotion);

        AvailableFilm availableFilm = DtoToEntity(availableFilmDto);
        AvailableFilm createdAvailableFilm = availableFilmRepository.save(availableFilm);
        return EntityToDto(createdAvailableFilm);
    }

    @Override
    public AvailableFilmDto updateAvailableFilm(Integer id, AvailableFilmDto availableFilmDto) {
        AvailableFilm availableFilmToUpdate = availableFilmRepository.findById(id).orElse(null);
        if (availableFilmToUpdate == null) {
            return null;
        }

        validateAvailableFilm(availableFilmDto);
        existsBusinessById(availableFilmDto.getBusiness().getId());
        existsFilmById(availableFilmDto.getFilm().getId());
        existsPromotionById(availableFilmDto.getPromotion().getId());

        Business business = businessRepository.findById(availableFilmDto.getBusiness().getId()).orElse(null);
        availableFilmDto.setBusiness(business);

        Film film = filmRepository.findById(availableFilmDto.getFilm().getId()).orElse(null);
        availableFilmDto.setFilm(film);

        Promotion promotion = promotionRepository.findById(availableFilmDto.getPromotion().getId()).orElse(null);
        availableFilmDto.setPromotion(promotion);

        availableFilmToUpdate.setBusiness(availableFilmDto.getBusiness());
        availableFilmToUpdate.setFilm(availableFilmDto.getFilm());
        availableFilmToUpdate.setCustomNotice(availableFilmDto.getCustomNotice());
        availableFilmToUpdate.setIsAvailable(availableFilmDto.getIsAvailable());
        availableFilmToUpdate.setPromotion(availableFilmDto.getPromotion());

        AvailableFilm updatedAvailableFilm = availableFilmRepository.save(availableFilmToUpdate);
        return EntityToDto(updatedAvailableFilm);
    }

    @Override
    public AvailableFilmDto deleteAvailableFilm(Integer id) {
        AvailableFilm availableFilmToDelete = availableFilmRepository.findById(Integer.valueOf(id)).orElse(null);
        if (availableFilmToDelete == null) {
            return null;
        }
        availableFilmRepository.delete(availableFilmToDelete);
        return EntityToDto(availableFilmToDelete);
    }

    private void validateAvailableFilm(AvailableFilmDto availableFilmDto) {
        if (availableFilmDto.getBusiness() == null) {
            throw new ResourceValidationException("Business id es requerido");
        }
        if (availableFilmDto.getFilm() == null) {
            throw new ResourceValidationException("Film id es requerido");
        }
        if (availableFilmDto.getIsAvailable() == null) {
            throw new ResourceValidationException("isAvailable es requerido");
        }
        if (availableFilmDto.getPromotion() == null) {
            throw new ResourceValidationException("Promotion id es requerido");
        }
    }

    private void existsBusinessById(Integer id) {
        if (!businessRepository.existsById(id)) {
            throw new ResourceValidationException("Business id not found");
        }
    }

    private void existsFilmById(Integer id) {
        if (!filmRepository.existsById(id)) {
            throw new ResourceValidationException("Film id not found");
        }
    }

    private void existsPromotionById(Integer id) {
        if (!promotionRepository.existsById(id)) {
            throw new ResourceValidationException("Promotion id not found");
        }
    }
}

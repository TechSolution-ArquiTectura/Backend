package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.ReviewDto;
import com.upc.TuCine.model.Review;
import com.upc.TuCine.repository.BusinessRepository;
import com.upc.TuCine.repository.ReviewRepository;
import com.upc.TuCine.service.ReviewService;
import com.upc.TuCine.shared.exception.ResourceValidationException;
import com.upc.TuCine.user.domain.model.entity.User;
import com.upc.TuCine.user.domain.persistence.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelMapper modelMapper;

    ReviewServiceImpl() { this.modelMapper = new ModelMapper(); }

    private ReviewDto EntityToDto(Review review) { return modelMapper.map(review, ReviewDto.class); }
    private Review DtoToEntity(ReviewDto reviewDto) { return modelMapper.map(reviewDto, Review.class); }

    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUserId(Integer userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
    public List<ReviewDto> getReviewsByBusinessId(Integer businessId) {
        List<Review> reviews = reviewRepository.findByBusinessId(businessId);
        return reviews.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUserIdAndBusinessId(Integer userId, Integer businessId) {
        List<Review> reviews = reviewRepository.findByUserIdAndBusinessId(userId, businessId);
        return reviews.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {

        validateReview(reviewDto);
        LocalDate currentDate = LocalDate.now();

        User user;
        try {
            user = userRepository.findById(reviewDto.getUser().getId()).orElse(null);
        } catch (Exception e) {
            user = null;
        }

        reviewDto.setCreatedAt(currentDate);
        reviewDto.setUpdatedAt(currentDate);
        reviewDto.setUser(user);

        Review review = DtoToEntity(reviewDto);
        return EntityToDto(reviewRepository.save(review));
    }

    private void validateReview(ReviewDto review) {
        if (review.getComment() == null || review.getComment().isEmpty()) {
            throw new ResourceValidationException("La reseña no puede ser nulo o estar vacío");
        }
        if (review.getRating() == null) {
            throw new ResourceValidationException("La calificacion no puede ser nula o estar vacía");
        }
        if (review.getUser() == null) {
            throw new ResourceValidationException("El usuario no puede ser nulo");
        }
    }
}

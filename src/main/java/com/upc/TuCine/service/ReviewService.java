package com.upc.TuCine.service;
import com.upc.TuCine.dto.ReviewDto;
import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    ReviewDto createReview(ReviewDto reviewDto);
    //ReviewDto getReviewById(Integer id);
    List<ReviewDto> getReviewsByUserId(Integer userId);
    List<ReviewDto> getReviewsByBusinessId(Integer businessId);
    List<ReviewDto> getReviewsByUserIdAndBusinessId(Integer userId, Integer businessId);

}

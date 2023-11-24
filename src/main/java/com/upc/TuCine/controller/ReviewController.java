package com.upc.TuCine.controller;

import com.upc.TuCine.dto.AvailableFilmDto;
import com.upc.TuCine.dto.PromotionDto;
import com.upc.TuCine.dto.ReviewDto;
import com.upc.TuCine.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/TuCine/v1")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @Transactional
    @PostMapping("/reviews")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(reviewDto), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/reviews/{businessId}")
    public ResponseEntity<List<ReviewDto>> getAllByBusinessId(@PathVariable(value = "businessId") Integer businessId) {
        return new ResponseEntity<>(reviewService.getReviewsByBusinessId(businessId), HttpStatus.OK);
    }

    // Delete review
    @Transactional
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable(value = "reviewId") Integer reviewId) {
        ReviewDto deletedReview = reviewService.deleteReview(reviewId);
        if (deletedReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(deletedReview, HttpStatus.OK);
    }
}
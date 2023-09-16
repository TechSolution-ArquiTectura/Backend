package com.upc.TuCine.controller;

import com.upc.TuCine.dto.AvailableFilmDto;
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
}

package com.nznext.geargrove.reviews.controllers;

import com.nznext.geargrove.reviews.dtos.CreateNewReviewDto;
import com.nznext.geargrove.reviews.entities.ReviewEntity;
import com.nznext.geargrove.reviews.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}/all")
    public List<ReviewEntity> getAllProducts(@PathVariable("productId") Long productId) {
        return reviewService.getAllReviews(productId);
    }

    @PostMapping("/create")
    public ReviewEntity create(@RequestBody CreateNewReviewDto createNewReviewDto) {
        return reviewService.createReview(createNewReviewDto);
    }

    @DeleteMapping("/{reviewId}/delete")
    public void delete(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}

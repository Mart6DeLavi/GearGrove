package com.nznext.geargrove.reviews.services;

import com.nznext.geargrove.products.entities.Product;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.repositories.ProductRepository;
import com.nznext.geargrove.reviews.dtos.CreateNewReviewDto;
import com.nznext.geargrove.reviews.entities.ReviewEntity;
import com.nznext.geargrove.reviews.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public List<ReviewEntity> getAllReviews(Long productId) {
        return reviewRepository.findAllById(Collections.singleton(productId));
    }

    public ReviewEntity createReview(CreateNewReviewDto createNewReviewDto) {
        return Stream.of(createNewReviewDto)
                .map(dto -> {
                    ReviewEntity review = new ReviewEntity();

                    review.setUsername(dto.getUsername());
                    review.setReview(dto.getReview());
                    review.setStars(dto.getStars());

                    Object product = productRepository.findById(dto.getProductId())
                            .orElseThrow(() -> new NoSuchProductException("Product not found with id: " + dto.getProductId()));

                    review.setProduct((Product) product);
                    return review;
                })
                .findFirst()
                .map(reviewRepository::save)
                .orElse(null);
    }

    public String deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return "Review deleted";
    }
}

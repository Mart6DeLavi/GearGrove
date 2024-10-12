package com.nznext.geargrove.reviews.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CreateNewReviewDto {
    private String username;
    private String review;
    private int stars;
    private Long productId;
}

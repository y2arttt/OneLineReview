package com.oneLineReview.oneLineReview.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RecommendReviewDTO {
    private String userId;
    private String reviewId;
    private int recommend;
}

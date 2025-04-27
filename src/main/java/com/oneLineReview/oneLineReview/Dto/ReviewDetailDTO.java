package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReviewDetailDTO {
    private Long id;
    private String body;
    private double rating;
    private String createdAt;
    private Long bookId;
    private Long userId;
    private int recommend;
    private String nickname;
    private String image;
    private String title;
    private String isbn;
}

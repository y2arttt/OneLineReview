package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ReviewFormDTO {
    private String bookId;
    private String comment;
    private String rating;
}

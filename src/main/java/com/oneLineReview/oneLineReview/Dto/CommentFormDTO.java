package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor

public class CommentFormDTO {
    private String reviewId;
    private String comment;
}

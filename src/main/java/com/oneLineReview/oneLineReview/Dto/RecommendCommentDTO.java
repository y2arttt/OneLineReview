package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class RecommendCommentDTO {
    private Long commentId;
    private String userId;
    private int recommend;
}

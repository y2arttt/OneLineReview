package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentResponseDTO {
    private String id;
    private String reviewId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime recentAt;
    private String userId;
}

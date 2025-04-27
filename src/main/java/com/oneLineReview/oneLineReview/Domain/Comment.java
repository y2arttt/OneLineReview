package com.oneLineReview.oneLineReview.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Comment {
    private int id;
    private String review_id;
    private String user_id;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime recent_at;


}

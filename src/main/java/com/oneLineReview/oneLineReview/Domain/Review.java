package com.oneLineReview.oneLineReview.Domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private String id;
    private String body;
    private String book_id;
    private String user_id;
    private String rating;
    private LocalDateTime created_at;
    private LocalDateTime recent_at;

}

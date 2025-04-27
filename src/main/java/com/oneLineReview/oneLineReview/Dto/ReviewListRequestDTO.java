package com.oneLineReview.oneLineReview.Dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter @Setter @NoArgsConstructor
public class ReviewListRequestDTO {
        private String isbn;
        private String type;
        private String bookId;
        private LocalDateTime recent;
        private String recentId;

}

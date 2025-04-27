package com.oneLineReview.oneLineReview.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter @Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ReviewUpdateFormDTO {
    private Long id;
    private String body;
    private String bookId;
    private double rating;
    private String isbn;
}

package com.oneLineReview.oneLineReview.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private Long id;
    private String title;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    private String pubdate;
    private String description;
    private String isbn; //국제표준도서번호


}

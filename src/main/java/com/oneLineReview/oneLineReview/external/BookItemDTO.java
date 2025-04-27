package com.oneLineReview.oneLineReview.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oneLineReview.oneLineReview.Domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookItemDTO {
    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("author")
    private String author;


    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("pubdate")
    private String pubdate;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("description")
    private String description;

    public static Book toVo(BookItemDTO dto) {
        if (dto == null) return null;

        Book vo = new Book();
        vo.setTitle(dto.getTitle());
        vo.setImage(dto.getImage());
        vo.setAuthor(dto.getAuthor());
        vo.setPublisher(dto.getPublisher());
        vo.setPubdate(dto.getPubdate());
        vo.setIsbn(dto.getIsbn());
        vo.setDescription(dto.getDescription());
        return vo;
    }
}

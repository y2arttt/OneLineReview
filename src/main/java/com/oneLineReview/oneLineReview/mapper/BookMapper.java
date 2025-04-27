package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Domain.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    // 도서 삽입
    int insert(Book bookVo);

    // ID로 단일 도서 조회
    Book select(String isbn);

    String idToIsbn(Long id);
    // 제목 또는 저자 검색
//    List<BookVo> selectList(String keyword);
}

package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.common.exception.customException.BookException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.BookErrorCode;
import com.oneLineReview.oneLineReview.external.BookItemDTO;
import com.oneLineReview.oneLineReview.external.NaverAPI;
import com.oneLineReview.oneLineReview.mapper.BookMapper;
import com.oneLineReview.oneLineReview.Domain.Book;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class BookService {



    private final NaverAPI naverAPI;
    private final BookMapper bookMapper;


    public void insertBook(Book bookVo) {

        bookMapper.insert(bookVo);

        Long bookId = bookVo.getId();
        if (bookId == null) {
            throw new BookException(BookErrorCode.BOOK_NOT_FOUND_DB,bookVo.getIsbn());
        }

    }


    public Book findOrFetchBook(String isbn) {
        try {
            return getBook(isbn);

        } catch (BookException e) {

            BookItemDTO bookItemDTO = naverAPI.ISBNSearch(isbn);

            Book bookVo = BookItemDTO.toVo(bookItemDTO);
            insertBook(bookVo);
            return bookVo;
        }

    }



    public Book getBook(String isbn) {
         Book bookVo = bookMapper.select(isbn);
        if (bookVo == null) {
            throw new BookException(BookErrorCode.BOOK_NOT_FOUND_DB,isbn);
        }
         return bookVo;

    }
    public String idToIsbn(String id) {
        return bookMapper.idToIsbn(Long.parseLong(id));
    }



}




package com.oneLineReview.oneLineReview.controller;

import com.oneLineReview.oneLineReview.Dto.ReviewDetailDTO;
import com.oneLineReview.oneLineReview.Dto.ReviewListRequestDTO;
import com.oneLineReview.oneLineReview.common.customannotation.UserId;
import com.oneLineReview.oneLineReview.common.exception.customException.NaverAPIException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.NaverErrorCode;
import com.oneLineReview.oneLineReview.external.NaverResult;
import com.oneLineReview.oneLineReview.service.BookService;
import com.oneLineReview.oneLineReview.service.ReviewService;
import com.oneLineReview.oneLineReview.Domain.Book;
import com.oneLineReview.oneLineReview.external.NaverAPI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@PropertySource("classpath:apikey.properties")
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    private final NaverAPI naverAPI;
    // 책 상세정보 - DataBase
    @GetMapping("/info/{isbn}")
    public String getBookById(
            @UserId String userId,
            @PathVariable String isbn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model){

        Book book = bookService.findOrFetchBook(isbn);
        model.addAttribute("book", book);

        // 초기 리뷰 목록 로딩 (첫 페이지)
        List<ReviewDetailDTO> reviewDetailDTOList = reviewService.reviewList(
                ReviewListRequestDTO.builder()
                        .bookId(String.valueOf(book.getId())).build(),
                size, page * size);
        model.addAttribute("reviewDetailDTOList", reviewDetailDTOList);

        if (userId != null) {
            ReviewDetailDTO myReview = reviewService.myReview(userId, isbn);
            model.addAttribute("myReview", myReview);
        }
        return "book/bookInfo";
    }

    private List<ReviewDetailDTO> getReviewList(String isbn) {
        ReviewListRequestDTO reviewListRequest = new ReviewListRequestDTO();
        reviewListRequest.setIsbn(isbn);
        return reviewService.reviewList(reviewListRequest,10,0);
    }


    // 책 겁색 - API
    @GetMapping("/search")
    public String searchAPI(Model model, @RequestParam String keyword, @RequestParam(defaultValue = "1") int page) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new NaverAPIException(NaverErrorCode.EMPTY_KEYWORD);
        }

        NaverResult result = naverAPI.searchAPI(keyword, page);
        model.addAttribute("result", result);

        return "book/bookSearch";
    }





}

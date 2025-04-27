package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.Domain.Review;
import com.oneLineReview.oneLineReview.Dto.*;
import com.oneLineReview.oneLineReview.common.exception.customException.ReviewException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.ReviewErrorCode;
import com.oneLineReview.oneLineReview.mapper.BookMapper;
import com.oneLineReview.oneLineReview.mapper.ReviewMapper;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {


    private final ReviewMapper reviewMapper;
    private final BookMapper bookMapper;
    private final CommentService commentService;
    private final BookService bookService;


    public String createReviewFormFromUser(ReviewFormDTO reviewFormDTO,String userId) {

        String isbn = bookMapper.idToIsbn(Long.valueOf(reviewFormDTO.getBookId()));
        if(reviewMapper.myReview(userId,isbn) != null)
            throw new ReviewException(ReviewErrorCode.DUPLICATE_REVIEW);
        Review review = createReview(reviewFormDTO, userId);

        reviewMapper.insertReview(review);
        Long id = Long.valueOf(review.getBook_id());
        return bookMapper.idToIsbn(id);
    }

    private static Review createReview(ReviewFormDTO reviewFormDTO, String userId) {
        return Review.builder()
                .user_id(userId)
                .book_id(reviewFormDTO.getBookId())
                .body(reviewFormDTO.getComment())
                .rating(reviewFormDTO.getRating())
                .created_at(LocalDateTime.now())
                .recent_at(LocalDateTime.now())
                .build();
    }

    public boolean updateReviewFormFromUserId (ReviewUpdateFormDTO updateFormDTO) {

        Review review = changeReviewFromUpdateForm(updateFormDTO);

        return reviewMapper.updateReview(review);
    }

    private Review changeReviewFromUpdateForm(ReviewUpdateFormDTO updateFormDTO) {
        return Review.builder()
                .id(String.valueOf(updateFormDTO.getId()))
                .rating(String.valueOf(updateFormDTO.getRating()))
                .body(updateFormDTO.getBody())
                .recent_at(LocalDateTime.now())
                .build();
    }





    public String deleteReviewAndGetIsbn(String reviewId) {

        commentService.deleteFromReviewId(Long.valueOf(reviewId));

        String bookId = findBookIdFromReviewId(Long.valueOf(reviewId));

        reviewMapper.deleteReview(Long.valueOf(reviewId));

        return bookService.idToIsbn(bookId);
    }

    public List<ReviewDetailDTO> reviewList(ReviewListRequestDTO reviewListRequestDTO, int i) {
        ReviewListRequestDTO reviewListRequest = new ReviewListRequestDTO();
        return reviewMapper.list(reviewListRequest,i,0);
    }

    public List<ReviewDetailDTO> reviewList(ReviewListRequestDTO reviewListRequest, int limit, int offset) {
        return reviewMapper.list(reviewListRequest,limit,offset);
    }


    public ReviewDetailDTO selectReview(String reviewId) {
        return reviewMapper.selectReview(reviewId);}


    public ReviewDetailDTO myReview(String userId, String isbn) {
        return reviewMapper.myReview(userId,isbn);
    }

    public Long findUserIdFromReviewId(Long reviewId) {
        return reviewMapper.checkUserIdFromReviewId(reviewId);
    }

    public String findBookIdFromReviewId(Long reviewId) {
        return reviewMapper.findBookIdToReviewId(reviewId);
    }

    public boolean validateUserIdFromReviewId(String userId, Long reviewId) {

        long reviewOwnerId = findUserIdFromReviewId(reviewId);
        long userIdLong = Long.parseLong(userId);
        return userIdLong ==reviewOwnerId;
    }
}

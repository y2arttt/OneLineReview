package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Domain.Review;
import com.oneLineReview.oneLineReview.Dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    boolean insertReview(Review review);

    boolean updateReview(Review review);

    boolean deleteReview(Long reviewId);

    ReviewDetailDTO selectReview(String reviewId);

    ReviewDetailDTO myReview(String userId,String isbn);

    List<ReviewDetailDTO> list(@Param("reviewListRequest") ReviewListRequestDTO reviewListRequest,
                               @Param("limit") int limit,
                               @Param("offset") int offset);
    Long checkUserIdFromReviewId(Long userId);

    String findBookIdToReviewId(Long userId);
}

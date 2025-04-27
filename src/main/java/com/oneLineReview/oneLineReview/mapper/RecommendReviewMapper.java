package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Dto.RecommendReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendReviewMapper {
    int insert(RecommendReviewDTO recommendReviewDTO);
    int delete(RecommendReviewDTO recommendReviewDTO);
    Integer isRecommended(RecommendReviewDTO recommendReviewDTO);
}

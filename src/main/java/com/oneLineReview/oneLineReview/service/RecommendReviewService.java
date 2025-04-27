package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.Dto.RecommendReviewDTO;
import com.oneLineReview.oneLineReview.mapper.RecommendReviewMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RecommendReviewService {


    private final RecommendReviewMapper recommendReviewMapper;

    public void recommendReview(String userId, String reviewId) {

        RecommendReviewDTO recommendReviewDTO = new RecommendReviewDTO();
        recommendReviewDTO.setUserId(userId);
        recommendReviewDTO.setReviewId(reviewId);

        if (isRecommended(recommendReviewDTO)) return;

        recommendReviewMapper.insert(recommendReviewDTO);
    }
    public void cancelRecommend(String userId, String reviewId) {



        RecommendReviewDTO dto = new RecommendReviewDTO();
        dto.setUserId(userId);
        dto.setReviewId(reviewId);

        if (!isRecommended(dto)) return;

        recommendReviewMapper.delete(dto);
    }


    public boolean isRecommended(RecommendReviewDTO dto) {
        return recommendReviewMapper.isRecommended(dto) != null;
    }



}

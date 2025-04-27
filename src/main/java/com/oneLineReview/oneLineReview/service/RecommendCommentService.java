package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.Dto.RecommendCommentDTO;
import com.oneLineReview.oneLineReview.mapper.RecommendCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendCommentService {

    private final RecommendCommentMapper recommendCommentMapper;

    public boolean recommendComment (RecommendCommentDTO recommendCommentDTO) {
        return recommendCommentMapper.insert(recommendCommentDTO) > 0;
    }

    public boolean cancelRecommendComment (RecommendCommentDTO recommendCommentDTO) {
        return recommendCommentMapper.delete(recommendCommentDTO) > 0;
    }


}

package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Dto.RecommendCommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendCommentMapper {
    int insert(RecommendCommentDTO recommendCommentDTO);
    int delete(RecommendCommentDTO recommendCommentDTO);
}

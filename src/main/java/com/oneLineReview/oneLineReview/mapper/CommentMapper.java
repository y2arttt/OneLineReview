package com.oneLineReview.oneLineReview.mapper;

import com.oneLineReview.oneLineReview.Domain.Comment;
import com.oneLineReview.oneLineReview.Dto.CommentResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    boolean insert(Comment comment);
    int delete(String id);
    void deleteFromReviewId(Long id);
    List<CommentResponseDTO> list(String reviewId);
    long selectReviewIdFromId(long id);
    long selectUserIdFromId(long id);
}

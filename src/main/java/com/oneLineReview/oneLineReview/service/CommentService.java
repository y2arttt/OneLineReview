package com.oneLineReview.oneLineReview.service;

import com.oneLineReview.oneLineReview.Domain.Comment;
import com.oneLineReview.oneLineReview.Dto.CommentFormDTO;
import com.oneLineReview.oneLineReview.Dto.CommentResponseDTO;
import com.oneLineReview.oneLineReview.common.exception.customException.CommentException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.CommentErrorCode;
import com.oneLineReview.oneLineReview.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentMapper commentMapper;
    public void createCommentFromUserId(CommentFormDTO commentFormDTO, String userId) {

        Comment comment = Comment.builder()
                .review_id(commentFormDTO.getReviewId())
                .user_id(userId)
                .content(commentFormDTO.getComment())
                .created_at(LocalDateTime.now())
                .recent_at(LocalDateTime.now())
                .build();
        commentMapper.insert(comment);
    }


    public boolean delete(Long id) {
        int deletedRow = commentMapper.delete(id.toString());
        if (deletedRow == 0) {
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND_DB);
        }
        return true;
    }

    public void deleteFromReviewId(Long reviewId){
        commentMapper.deleteFromReviewId(reviewId);

    }


    public List<CommentResponseDTO> list(String reviewId) {

        return commentMapper.list(reviewId);
    }

    public long findReviewIdFromId (long id){
        return commentMapper.selectReviewIdFromId(id);
    }

    public long findUserIdFromId (long id){
        return commentMapper.selectUserIdFromId(id);
    }

    public boolean validateUserIdFromCommentId(String userId,String id){
        long commentUserId = findUserIdFromId(Long.parseLong(id));
        long userIdLong = Long.parseLong(userId);

        return commentUserId == userIdLong;
    }




}

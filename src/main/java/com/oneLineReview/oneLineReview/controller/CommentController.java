package com.oneLineReview.oneLineReview.controller;

import com.oneLineReview.oneLineReview.Dto.CommentFormDTO;
import com.oneLineReview.oneLineReview.common.customannotation.ValidateOwnership;
import com.oneLineReview.oneLineReview.service.CommentService;
import com.oneLineReview.oneLineReview.common.customannotation.UserId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.oneLineReview.oneLineReview.common.customannotation.ValidateOwnership.*;

@Slf4j
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/add")
    public String addComment(@UserId String userId,
                             CommentFormDTO commentFormDTO,
                             HttpServletRequest request) {


        commentService.createCommentFromUserId(commentFormDTO,userId);

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }


    @PostMapping("/delete")
    @ValidateOwnership(service = SERVICE.COMMENT)
    public String deleteComment(@RequestParam Long id) {

        long reviewId = commentService.findReviewIdFromId(id);
        commentService.delete(id);

        return "redirect:/review/detail/"+reviewId ;
    }


}

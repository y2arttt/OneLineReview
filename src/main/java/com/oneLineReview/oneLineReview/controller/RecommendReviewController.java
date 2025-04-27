package com.oneLineReview.oneLineReview.controller;


import com.oneLineReview.oneLineReview.common.customannotation.UserId;
import com.oneLineReview.oneLineReview.service.RecommendReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review/recommend")
@RequiredArgsConstructor
public class RecommendReviewController {


    private final RecommendReviewService recommendReviewService;

    @PostMapping("/{reviewId}")
    public String handleRecommendReview(@UserId String userId,
                                        @PathVariable String reviewId,
                                        @RequestParam(required = false) String action) {

        if ("delete".equals(action)) {
            recommendReviewService.cancelRecommend(userId, reviewId);
        } else {
            recommendReviewService.recommendReview(userId, reviewId);
        }

        return "redirect:/review/detail/" + reviewId;
    }



}

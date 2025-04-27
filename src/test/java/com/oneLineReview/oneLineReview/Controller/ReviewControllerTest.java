package com.oneLineReview.oneLineReview.Controller;

import com.oneLineReview.oneLineReview.controller.ReviewController;
import com.oneLineReview.oneLineReview.service.CommentService;
import com.oneLineReview.oneLineReview.service.RecommendReviewService;
import com.oneLineReview.oneLineReview.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean  // Mock ReviewService 생성
    private ReviewService reviewService;
    @MockitoBean
    private CommentService commentService;
    @MockitoBean // 이 부분을 추가
    private RecommendReviewService recommendReviewService;

    @Test
    void postUpdateReviewTest() throws Exception {
        // given
        Long reviewId = 14L;
        String sessionUserId = "1";
        String isbn = "999";

        when(reviewService.updateReviewFormFromUserId(any())).thenReturn(true);
        when(reviewService.findUserIdFromReviewId(reviewId)).thenReturn(1L);
        mockMvc.perform(post("/review/update")
                        .sessionAttr("userId", sessionUserId)
                        .param("id", String.valueOf(reviewId))
                        .param("isbn", isbn)
                        .param("body", "수정 내용")
                        .param("rating", "4")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book/info/" + isbn));
    }

}

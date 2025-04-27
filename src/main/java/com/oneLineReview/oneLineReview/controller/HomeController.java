package com.oneLineReview.oneLineReview.controller;

import com.oneLineReview.oneLineReview.Dto.ReviewDetailDTO;
import com.oneLineReview.oneLineReview.Dto.ReviewListRequestDTO;
import com.oneLineReview.oneLineReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ReviewService reviewService;

    @GetMapping("/")
    public String home(Model model) {
        List<ReviewDetailDTO> reviewDetailDTOList = reviewService.reviewList(new ReviewListRequestDTO(), 10);
        model.addAttribute("reviewDetailDTOList", reviewDetailDTOList);
        return "basic/main";
    }
}

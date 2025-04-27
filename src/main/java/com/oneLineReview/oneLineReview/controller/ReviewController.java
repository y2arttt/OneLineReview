package com.oneLineReview.oneLineReview.controller;

import com.oneLineReview.oneLineReview.Domain.Book;
import com.oneLineReview.oneLineReview.Dto.*;
import com.oneLineReview.oneLineReview.common.customannotation.UserId;
import com.oneLineReview.oneLineReview.Dto.CommonResponse;
import com.oneLineReview.oneLineReview.common.customannotation.ValidateOwnership;
import com.oneLineReview.oneLineReview.service.BookService;
import com.oneLineReview.oneLineReview.service.CommentService;
import com.oneLineReview.oneLineReview.service.RecommendReviewService;
import com.oneLineReview.oneLineReview.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.oneLineReview.oneLineReview.common.customannotation.ValidateOwnership.*;

@Slf4j
@RequestMapping("/review")
@Controller
@RequiredArgsConstructor
public class ReviewController {


    private final ReviewService reviewService;
    private final CommentService commentService;
    private final RecommendReviewService recommendReviewService;
    private final BookService bookService;
    /** 리뷰 생성 **/

    @GetMapping("/write/{bookId}")
    public String writeReview(Model model ,@PathVariable String  bookId) {
        ReviewFormDTO dto = new ReviewFormDTO();
        dto.setBookId(bookId);
        model.addAttribute("reviewFormDTO", dto);
        return "/review/writeReviewForm";
    }

    @PostMapping("/write")
    public String writeReview(@UserId String id, ReviewFormDTO reviewFormDTO) {

        String isbn = reviewService.createReviewFormFromUser(reviewFormDTO,id);

        return "redirect:/book/info/" + isbn;
    }

    /** 리뷰 수정 **/

    @GetMapping("/update/{isbn}")
    public String updateReview(@UserId String userId, @PathVariable String isbn, Model model) {

        ReviewDetailDTO reviewDetailDTO = reviewService.myReview(userId,isbn);
        model.addAttribute("reviewDetailDTO", reviewDetailDTO);

        return "/review/updateReviewForm";

    }

    @ValidateOwnership(service = SERVICE.REVIEW)
    @PostMapping("/update")
    public String updateReview(ReviewUpdateFormDTO updateFormDTO,
                               RedirectAttributes redirectAttributes) {


        reviewService.updateReviewFormFromUserId(updateFormDTO);
        redirectAttributes.addFlashAttribute("message", "리뷰가 성공적으로 수정되었습니다.");
        return "redirect:/book/info/" + updateFormDTO.getIsbn();
    }

    /** 리뷰 삭제 **/
    @ValidateOwnership(service = SERVICE.REVIEW)
    @PostMapping("/delete")
    public String  deleteReview(String id) {

        String isbn = reviewService.deleteReviewAndGetIsbn(id);

        return "redirect:/book/info/" +isbn ;

    }

    //모든 리뷰 조회
    @GetMapping("/list")
    public String reviewList(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        ReviewListRequestDTO reviewListRequest = new ReviewListRequestDTO();
        int offset = page * size;
        List<ReviewDetailDTO> result = reviewService.reviewList(reviewListRequest, size, offset);

        model.addAttribute("reviewDetailDTOList", result);
        return "fragments/review/reviewCardList :: reviewCardList";
    }



    @GetMapping("/bookInfo/{isbn}")
    public String getMoreReviews(
            @PathVariable String isbn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Book book = bookService.findOrFetchBook(isbn);
        List<ReviewDetailDTO> reviewDetailDTOList = reviewService.reviewList(
                ReviewListRequestDTO.builder()
                        .bookId(String.valueOf(book.getId()))
                        .build(),
                size, page * size);

        // reviewDetailDTOList라는 이름으로 모델에 추가
        model.addAttribute("reviewDetailDTOList", reviewDetailDTOList);

        // 템플릿 경로와 변수명 수정
        return "fragments/book/bookCardList :: reviewCardList";
    }

    @GetMapping("/detail/{id}")
    public String getReviewDetail(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @PathVariable String id,
                                  Model model) {

        //댓글 삭제시 캐시 삭제를 위해서
        response.setHeader("Cache-Control", "no-store");

        ReviewDetailDTO result = reviewService.selectReview(id);
        model.addAttribute("review", result);

        List<CommentResponseDTO> commentDTOList = commentService.list(id);
        model.addAttribute("comments", commentDTOList);
        model.addAttribute("commentFormDTO",new CommentFormDTO());
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            RecommendReviewDTO recommendReviewDTO = new RecommendReviewDTO();
            recommendReviewDTO.setUserId(userId);
            recommendReviewDTO.setReviewId(id);
            model.addAttribute("isRecommended",
                    recommendReviewService.isRecommended(recommendReviewDTO));

        }

        return "/review/reviewDetail";

    }

    @GetMapping("/myReview/{isbn}")
    public ResponseEntity<CommonResponse<ReviewDetailDTO>> myReview(@UserId String userId, @PathVariable String isbn) {
        try {
            ReviewDetailDTO result = reviewService.myReview(userId, isbn);
            return ResponseEntity.ok(new CommonResponse<>(true, result));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(false, null));
        }
    }
}

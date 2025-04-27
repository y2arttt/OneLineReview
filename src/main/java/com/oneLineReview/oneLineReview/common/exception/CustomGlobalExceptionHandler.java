package com.oneLineReview.oneLineReview.common.exception;

import com.oneLineReview.oneLineReview.common.exception.customException.BasicException;
import com.oneLineReview.oneLineReview.common.exception.customException.JoinException;
import com.oneLineReview.oneLineReview.common.exception.customException.LoginAuthException;
import com.oneLineReview.oneLineReview.common.exception.customException.NaverAPIException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler {



    @ExceptionHandler(JoinException.class)
    public String handleJoinException(JoinException ex, Model model, HttpServletResponse response) {
        ErrorCode errorCode = ex.getCustomExceptionType();
        response.setStatus(errorCode.getStatus().value());
        model.addAttribute("errorMessage", ex.getMessage());
        return "auth/joinForm";
    }

    @ExceptionHandler(LoginAuthException.class)
    public String handleLoginAuthException(LoginAuthException ex,
                                           RedirectAttributes redirectAttributes,
                                           HttpServletResponse response) {
        ErrorCode errorCode = ex.getCustomExceptionType();

        response.setStatus(errorCode.getStatus().value());
        if(!ex.getMessage().isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/auth/login";
    }


    @ExceptionHandler(BasicException.class)
    public String handleBasicException(BasicException ex, Model model, HttpServletResponse response) {
        ErrorCode errorCode = ex.getCustomExceptionType();
        response.setStatus(errorCode.getStatus().value());
        model.addAttribute("errorMessage", ex.getMessage());
        return selectErrorPage(errorCode);
    }

    @ExceptionHandler(NaverAPIException.class)
    public String handleNaverAPIException(NaverAPIException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "book/bookSearch";
    }


    //Util=======================================//

    private static String selectErrorPage(ErrorCode errorCode) {
        return switch (errorCode.getStatus()) {
            case NOT_FOUND -> "error/404errorPage";
//            case BAD_REQUEST -> "error/400errorPage";
//            case UNAUTHORIZED -> "error/401errorPage";
//            case FORBIDDEN -> "error/403errorPage";
            default -> "error/500errorPage";
        };
    }
}

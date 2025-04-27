package com.oneLineReview.oneLineReview.common.exception;

import com.oneLineReview.oneLineReview.Dto.JoinDTO;
import com.oneLineReview.oneLineReview.common.exception.customException.BasicException;
import com.oneLineReview.oneLineReview.common.exception.customException.JoinException;
import com.oneLineReview.oneLineReview.common.exception.customException.LoginAuthException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {

        Object target = ex.getBindingResult().getTarget();
        if (target instanceof JoinDTO joinDTO) {
            return handleJoinValidationException(ex, model, joinDTO);
        }

        model.addAttribute("errorMessage", "요청이 잘못되었습니다.");
        return "error/500errorPage";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException ex, RedirectAttributes redirectAttributes) {
        return "redirect:/auth/login";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException ex, Model model) {
        model.addAttribute("errorMessage", "요청하신 페이지를 찾을 수 없습니다.");
        return "error/404errorPage";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(DataAccessException ex, Model model) {
        model.addAttribute("errorMessage", "데이터 처리 중 오류가 발생했습니다.");
        return "error/500errorPage";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "서버에 오류가 발생했습니다.");
        return "error/500errorPage";
    }


    //Util=======================================//

    private String handleJoinValidationException(MethodArgumentNotValidException ex, Model model, JoinDTO joinDTO) {
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage());
        }

        model.addAttribute("joinDTO", joinDTO);
        model.addAttribute("errorMessage", errorMessages);
        return "auth/joinForm";
    }


}
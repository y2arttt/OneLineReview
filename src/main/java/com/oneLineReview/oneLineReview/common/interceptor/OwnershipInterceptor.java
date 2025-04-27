package com.oneLineReview.oneLineReview.common.interceptor;

import com.oneLineReview.oneLineReview.common.customannotation.ValidateOwnership;
import com.oneLineReview.oneLineReview.common.exception.customException.BasicException;
import com.oneLineReview.oneLineReview.common.exception.customException.LoginAuthException;
import com.oneLineReview.oneLineReview.common.exception.errorCode.BasicErrorCode;
import com.oneLineReview.oneLineReview.common.exception.errorCode.UserErrorCode;
import com.oneLineReview.oneLineReview.constant.UserRole;
import com.oneLineReview.oneLineReview.service.AuthService;
import com.oneLineReview.oneLineReview.service.CommentService;
import com.oneLineReview.oneLineReview.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnershipInterceptor implements HandlerInterceptor {
    private final ReviewService reviewService;
    private final CommentService commentService;
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ValidateOwnership annotation = handlerMethod.getMethodAnnotation(ValidateOwnership.class);

        if (annotation == null) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            throw new LoginAuthException(UserErrorCode.UNLOGGED_USER);
        }

        String userId = (String) session.getAttribute("userId");
        String resourceId = request.getParameter("id");

        if (resourceId == null) {
            throw new BasicException(BasicErrorCode.BAD_REQUEST);
        }

        if (authService.roleCheck(userId).equals(UserRole.ROLE_ADMIN)){
            return true;
        }


        boolean isValid = switch (annotation.service()) {
            case REVIEW -> reviewService.validateUserIdFromReviewId(userId, Long.parseLong(resourceId));
            case COMMENT -> commentService.validateUserIdFromCommentId(userId, resourceId);

        };

        if (!isValid) {
            throw new LoginAuthException(UserErrorCode.UNAUTHORIZED_USER);
        }

        return true;
    }
}
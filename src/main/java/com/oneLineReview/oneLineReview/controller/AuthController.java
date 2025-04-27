package com.oneLineReview.oneLineReview.controller;

import com.oneLineReview.oneLineReview.service.AuthService;
import com.oneLineReview.oneLineReview.Dto.JoinDTO;
import com.oneLineReview.oneLineReview.Dto.LoginRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    private final AuthService authService;

    @GetMapping("/join/**")
    public String createUserForm(Model model) {

        model.addAttribute("joinDTO", new JoinDTO());
        return "auth/joinForm";
    }

    @PostMapping("/join")
    public String createUser(@Valid JoinDTO joinDTO) {
            log.info("UserController usersVo = {}", joinDTO);
            authService.register(joinDTO);
            return "redirect:/";
    }

    @GetMapping("/login/**")
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginRequestDTO());

        return "auth/loginForm";
    }

    @PostMapping("/login")
    public String login( LoginRequestDTO loginRequest, HttpServletRequest request ) {

        Long userId = authService.login(loginRequest);

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId.toString());

        log.info("user login [{}] {}",session.getId(),userId);


        return "redirect:/";

    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        String referer = request.getHeader("Referer");

        return "redirect:" + (referer != null ? referer : "/");
    }


}

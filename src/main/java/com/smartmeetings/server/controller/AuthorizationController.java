package com.smartmeetings.server.controller;

import com.smartmeetings.server.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {


    @Autowired
    UserAuthService authService;

    // Todo use enum for return result
    @PostMapping("/register")
    public @ResponseBody
    String registerUser(@RequestParam String email,
                        @RequestParam String name,
                        @RequestParam String password,
                        @RequestParam Optional<Integer> groupNumber,
                        HttpServletResponse response) {
        try {
            String token = authService.registerUser(email, name, password, groupNumber);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        } catch (NoSuchAlgorithmException exception) {
            return "ERROR";
        }
        return "OK";
    }

    // Todo use enum for return result
    @PostMapping("/login")
    public @ResponseBody
    String loginUser(@RequestParam String email,
                     @RequestParam String password,
                     HttpServletResponse response) {
        try {
            var opt = authService.loginUser(email, password);
            if (opt.isPresent()) {
                Cookie cookie = new Cookie("token", opt.get());
                response.addCookie(cookie);
                return "OK";
            }
        } catch (NoSuchAlgorithmException ignore) {
        }
        return "ERROR";
    }

    // Todo use enum for return result
    @PostMapping("/authenticate")
    public @ResponseBody
    String authUser(@RequestParam String email,
                    @CookieValue("token") String token,
                    HttpServletResponse response) {
        try {
            var opt = authService.authUser(email, token);
            if (opt.isPresent()) {
                String newToken = opt.get();
                Cookie cookie = new Cookie("token", newToken);
                response.addCookie(cookie);
                return "OK";
            }
        } catch (NoSuchAlgorithmException ignore) {
        }
        return "ERROR";
    }
}
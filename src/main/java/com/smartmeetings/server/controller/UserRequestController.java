package com.smartmeetings.server.controller;

import com.smartmeetings.server.data.User;
import com.smartmeetings.server.data.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserRequestController {

    final UserRepo userRepo;

    public UserRequestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/list")
    public @ResponseBody String usersListRequest() {
        return "All users: \n" + userRepo.findAllNames();
    }

    @PostMapping("/register")
    public @ResponseBody String registerUser(String eMail, String password, String telegramUsername) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(eMail);
        user.setPasswordHash(encoder.encode(password));
        user.setTelegramUsername(telegramUsername);
        userRepo.save(user);
        return "Ok";
    }
}

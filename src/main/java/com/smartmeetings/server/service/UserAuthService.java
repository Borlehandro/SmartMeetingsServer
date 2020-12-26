package com.smartmeetings.server.service;

import com.smartmeetings.server.data.Group;
import com.smartmeetings.server.data.GroupsRepo;
import com.smartmeetings.server.data.User;
import com.smartmeetings.server.data.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupsRepo groupsRepo;

    /**
     * @return new user token
     * @throws NoSuchAlgorithmException if can not generate token
     */
    public String registerUser(String email, String name, String password, Optional<Integer> groupNumberOpt)
            throws NoSuchAlgorithmException {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        if (groupNumberOpt.isPresent()) {
            groupsRepo.addGroup(groupNumberOpt.get());
            Group g = new Group();
            g.setGroupNumber(groupNumberOpt.get());
            user.setGroup(g);
        }
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        String token = generateToken();
        user.setToken(token);
        userRepo.save(user);
        return token;
    }

    public Optional<String> loginUser(String email, String password) throws NoSuchAlgorithmException {
        var opt = userRepo.findByEmail(email);
        if (opt.isPresent()) {
            User user = opt.get();
            if (new BCryptPasswordEncoder().matches(password, user.getPasswordHash())) {
                String token = generateToken();
                user.setToken(token);
                userRepo.save(user);
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

    // Todo refactor
    public Optional<String> authUser(String token) throws NoSuchAlgorithmException {
        var opt = userRepo.findOneByToken(token);
        if (opt.isPresent()) {
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public boolean checkAuthData(String email, String token) {
        return userRepo.existsUserByEmailAndToken(email, token);
    }

    private String generateToken() throws NoSuchAlgorithmException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(salt.digest());
    }
}
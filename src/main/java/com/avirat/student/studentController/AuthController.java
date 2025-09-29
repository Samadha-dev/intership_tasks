package com.avirat.student.studentController;

import com.avirat.student.studentEntity.UserEntity;
import com.avirat.student.studentRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Registration endpoint
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {
        UserEntity user = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_STUDENT")
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }
}

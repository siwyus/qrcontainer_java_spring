package com.siwyus.qrcontainer.services;

import com.siwyus.qrcontainer.model.User;
import com.siwyus.qrcontainer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);
    }

    public void loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid email or password.");
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }
    }
}

// todo
// - customowe błędy
// - walidacja danych
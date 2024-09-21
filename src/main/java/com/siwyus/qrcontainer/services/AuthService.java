package com.siwyus.qrcontainer.services;

import com.siwyus.qrcontainer.model.User;
import com.siwyus.qrcontainer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();

            return userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            throw new RuntimeException("User not authenticated");
        }
    }
}

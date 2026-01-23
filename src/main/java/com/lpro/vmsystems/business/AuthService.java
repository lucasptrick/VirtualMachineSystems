package com.lpro.vmsystems.business;

import com.lpro.vmsystems.dto.loginRequestDTO.LoginRequestDTO;
import com.lpro.vmsystems.dto.loginResponseDTO.LoginResponseDTO;
import com.lpro.vmsystems.infrastruture.entity.User;
import com.lpro.vmsystems.infrastruture.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        System.out.println("EMAIL REQ: " + request.getEmail());
        System.out.println("PASS REQ: " + request.getPassword());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        System.out.println("PASS DB: " + user.getPassword());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }


    public User getAuthenticatedUser(Integer userId) {
        if (userId == null) {
            throw new RuntimeException("User not authenticated");
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }

}


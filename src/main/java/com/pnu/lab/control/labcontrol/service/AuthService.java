package com.pnu.lab.control.labcontrol.service;

import com.pnu.lab.control.labcontrol.api.dto.auth.LoginRequest;
import com.pnu.lab.control.labcontrol.api.dto.auth.LoginResponse;
import com.pnu.lab.control.labcontrol.domain.User;
import com.pnu.lab.control.labcontrol.exception.EntityNotFoundException;
import com.pnu.lab.control.labcontrol.utils.JwtUtils;
import com.pnu.lab.control.labcontrol.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    public LoginResponse login(LoginRequest request) {
        User user = userService.getByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User is not found!"));

        LoginResponse response = new LoginResponse();
        response.setToken(jwtUtils.generateToken(user));

        return response;
    }

    public User whoami() {
        return UserUtils.getUserInfo();
    }
}

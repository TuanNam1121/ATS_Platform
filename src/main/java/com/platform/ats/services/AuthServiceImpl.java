package com.platform.ats.services;

import com.platform.ats.dao.AuthDao;
import com.platform.ats.dto.UserRegisterRequest;
import com.platform.ats.dto.UserRequest;
import com.platform.ats.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthDao authDao;

    @Override
    public User authenticate(UserRequest userRequest) {

        return authDao.login(userRequest);
    }

    @Override
    public Long register(UserRegisterRequest registerRequest) {
        // Convert to Entity


        // Validate

        return null;
    }
}

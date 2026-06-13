package com.platform.ats.services;

import com.platform.ats.dto.UserRegisterRequest;
import com.platform.ats.dto.UserRequest;
import com.platform.ats.entities.User;

public interface AuthService {
    User authenticate(UserRequest userRequest);

    Long register(UserRegisterRequest registerRequest);
}

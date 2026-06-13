package com.platform.ats.dao;

import com.platform.ats.dto.UserRequest;
import com.platform.ats.entities.User;


public interface AuthDao {
    User login(UserRequest userRequest);
    User register(User user);
}

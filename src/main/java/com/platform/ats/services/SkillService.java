package com.platform.ats.services;

import com.platform.ats.dto.SkillResponse;

import java.util.List;

public interface SkillService {
    List<SkillResponse> findAll();
}

package org.ats.services;

import org.ats.dto.SkillResponse;
import org.ats.entities.Skill;

import java.util.List;

public interface SkillService {
    List<SkillResponse> findAll();
}

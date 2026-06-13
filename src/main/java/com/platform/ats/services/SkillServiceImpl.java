package com.platform.ats.services;

import com.platform.ats.dao.SkillDao;
import com.platform.ats.dto.SkillResponse;
import com.platform.ats.entities.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillDao skillDao;

    @Override
    public List<SkillResponse> findAll() {
        List<Skill> skills = skillDao.findAll();


        return toDto(skills);
    }

    private List<SkillResponse> toDto(List<Skill> skills) {
        return skills.stream().map((skill) -> {
            return SkillResponse.builder()
                    .id(skill.getId())
                    .skillName(skill.getSkillName()).build();
        }).collect(Collectors.toList());
    }
}

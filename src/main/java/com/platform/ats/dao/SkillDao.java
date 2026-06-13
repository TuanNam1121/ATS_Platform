package com.platform.ats.dao;


import com.platform.ats.entities.Skill;
import java.util.List;

public interface SkillDao {
    Skill createSkill(Skill skill);

    void delete(Long id);

    Skill updateSkill(Skill skill);

    List<Skill> findByName(String keyword);

    List<Skill> findAll();

}

package org.ats.dao;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.ats.entities.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class SkillDaoImpl implements SkillDao {
    private final SessionFactory sessionFactory;

    @Override
    public Skill createSkill(Skill skill) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(skill);

        return skill;

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Skill skill = session.get(Skill.class, id);

        session.remove(skill);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(skill);
        return skill;

    }

    @Override
    public List<Skill> findByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Skill> query = session.createNamedQuery("findByName", Skill.class);
        query.setParameter("keyword", "%" + keyword + "%");

        return query.getResultList();
    }

    @Override
    public List<Skill> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Skill", Skill.class).list();
    }
}

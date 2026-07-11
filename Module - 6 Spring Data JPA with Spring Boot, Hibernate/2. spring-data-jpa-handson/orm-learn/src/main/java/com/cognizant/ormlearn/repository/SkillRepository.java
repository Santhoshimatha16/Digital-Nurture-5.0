package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Skill;

/**
 * Hands-on 3: SkillRepository
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}

package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}

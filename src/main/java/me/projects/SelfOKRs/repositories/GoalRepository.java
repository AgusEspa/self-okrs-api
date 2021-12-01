package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}

package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query(value = "SELECT * FROM GOALS WHERE USER_ID = ?1", nativeQuery = true)
    List<Goal> findByUserId(Long id);

}

package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.KeyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeyResultRepository extends JpaRepository<KeyResult, Long> {

    @Query(value = "SELECT * FROM KEY_RESULTS WHERE OBJECTIVE_ID = ?1", nativeQuery = true)
    List<KeyResult> findAllPerObjective(Long objectiveId);

}

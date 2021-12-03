package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

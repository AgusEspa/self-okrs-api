package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.TaskNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.KeyResultRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private final KeyResultRepository taskRepository;

    private final GoalRepository goalRepository;

    private final UserEntityRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public TaskService(KeyResultRepository repository, GoalRepository goalRepository, UserEntityRepository userRepository, AuthenticationFacade authenticationFacade) {
        this.taskRepository = repository;
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    // Refactor, not secure
    public List<KeyResult> all() {
        return taskRepository.findAll();
    }

    // Refactor, not secure
    public KeyResult one(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public KeyResult newTask(KeyResultRequest taskRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        UserEntity user = userRepository.findByEmailAddress(username);

        Goal goal = goalRepository.findById(taskRequest.getGoalId())
                .orElseThrow(() -> new GoalNotFoundException(taskRequest.getGoalId()));

        if (goal.getUser().getEmailAddress() == user.getEmailAddress()) {
            if (taskRequest.getDueDate() == null) {
                return taskRepository.save(new KeyResult(taskRequest.getName(), goal));
            } else {
                return taskRepository.save(new KeyResult(taskRequest.getName(), taskRequest.getDueDate(),goal));
            }
        } else throw new UserEntityNotFoundException(username);

    }

    // Refactor, not secure
    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }

    // Refactor, not secure
    public KeyResult editTask(Long id, KeyResult editedKeyResult) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(editedKeyResult.getName());
                    task.setDueDate(editedKeyResult.getDueDate());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}

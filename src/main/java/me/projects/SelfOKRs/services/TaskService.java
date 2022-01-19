package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.TaskRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.TaskNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.TaskRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    private final GoalRepository goalRepository;

    private final UserEntityRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public TaskService(TaskRepository repository, GoalRepository goalRepository, UserEntityRepository userRepository, AuthenticationFacade authenticationFacade) {
        this.taskRepository = repository;
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    // Refactor, not secure
    public List<Task> all() {
        return taskRepository.findAll();
    }

    // Refactor, not secure
    public Task one(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task newTask(TaskRequest taskRequest) {
        String username = authenticationFacade.getAuthentication().getName();
        UserEntity user = userRepository.findByEmailAddress(username);

        Goal goal = goalRepository.findById(taskRequest.getGoalId())
                .orElseThrow(() -> new GoalNotFoundException(taskRequest.getGoalId()));

        if (goal.getUser().getEmailAddress() == user.getEmailAddress()) {
            if (taskRequest.getDueDate() == null) {
                return taskRepository.save(new Task(taskRequest.getName(), goal));
            } else {
                return taskRepository.save(new Task(taskRequest.getName(), taskRequest.getDueDate(),goal));
            }
        } else throw new UserEntityNotFoundException(username);

    }

    // Refactor, not secure
    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }

    // Refactor, not secure
    public Task editTask(Long id, Task editedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(editedTask.getName());
                    task.setDueDate(editedTask.getDueDate());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}

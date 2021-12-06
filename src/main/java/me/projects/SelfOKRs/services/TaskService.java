package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.TaskNotFoundException;
import me.projects.SelfOKRs.pojos.TaskRequest;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    GoalRepository goalRepository;

    public TaskService() {}

    public List<Task> all() {
        return repository.findAll();
    }

    public Task one(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task newTask(TaskRequest taskRequest) {
        Goal goal = goalRepository.findById(taskRequest.getGoalId())
                .orElseThrow(() -> new GoalNotFoundException(taskRequest.getGoalId()));;
        return repository.save(new Task(taskRequest.getName(), goal));
    }

    public void deleteOne(Long id) {
        repository.deleteById(id);
    }
}

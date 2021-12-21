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

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    private final GoalRepository goalRepository;

    @Autowired
    public TaskService(TaskRepository repository, GoalRepository goalRepository) {
        this.taskRepository = repository;
        this.goalRepository = goalRepository;
    }

    public List<Task> all() {
        return taskRepository.findAll();
    }

    public Task one(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task newTask(TaskRequest taskRequest) {
        Goal goal = goalRepository.findById(taskRequest.getGoalId())
                .orElseThrow(() -> new GoalNotFoundException(taskRequest.getGoalId()));
        return taskRepository.save(new Task(taskRequest.getName(), goal));
    }

    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }

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

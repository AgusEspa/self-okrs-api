package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Task;
import me.projects.SelfOKRs.exceptions.TaskNotFoundException;
import me.projects.SelfOKRs.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public TaskService() {}

    public List<Task> all() {
        return repository.findAll();
    }

    public Task one(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task newTask(Task newTask) {
        //implementation with goal id
        return repository.save(newTask);
    }

    public void deleteOne(Long id) {
        repository.deleteById(id);
    }
}

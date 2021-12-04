package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;

    public GoalService() {}

    public List<Goal> all() {
        return goalRepository.findAll();
    }

    public Goal one(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    public Goal newGoal(Goal newGoal) {
        return goalRepository.save(newGoal);
    }

    public void deleteOne(Long id) {
        goalRepository.deleteById(id);
    }
}

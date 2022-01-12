package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.dtos.GoalRequest;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserEntityRepository userRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, UserEntityRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public List<Goal> all() {
        return goalRepository.findAll();
    }

    public Goal one(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    public Goal newGoal(GoalRequest goalRequest) {
        UserEntity user = userRepository.findById(goalRequest.getUserId())
                .orElseThrow(() -> new UserEntityNotFoundException(goalRequest.getUserId()));
        return goalRepository.save(new Goal(goalRequest.getName(),
                goalRequest.getImportance(),
                goalRequest.getProgressPercentage(),
                user));
    }

    public void deleteOne(Long id) {
        goalRepository.deleteById(id);
    }

    public Goal editGoal(Long id, Goal editedGoal) {
        return goalRepository.findById(id)
                .map(goal -> {
                    goal.setName(editedGoal.getName());
                    goal.setImportance(editedGoal.getImportance());
                    goal.setProgressPercentage(editedGoal.getProgressPercentage());
                    return goalRepository.save(goal);
                })
                .orElseThrow(() -> new GoalNotFoundException(id));
    }
}

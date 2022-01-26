package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.GoalRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserEntityRepository userRepository;

    Logger logger = LoggerFactory.getLogger(GoalService.class);


    @Autowired
    public GoalService(GoalRepository goalRepository, UserEntityRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public List<Goal> all() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return goalRepository.findByUserId(user.getId());
    }

    // Refactor, not secure
//    public Goal one(Long id) {
//        return goalRepository.findById(id)
//                .orElseThrow(() -> new GoalNotFoundException(id));
//    }

    public Goal newGoal(GoalRequest goalRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return goalRepository.save(new Goal(goalRequest.getName(),
                goalRequest.getImportance(),
                user));
    }

    // Refactor, not secure
    public void deleteOne(Long id) {
        goalRepository.deleteById(id);
    }

    // Refactor, not secure
    public Goal editGoal(Long id, Goal editedGoal) {
        return goalRepository.findById(id)
                .map(goal -> {
                    goal.setName(editedGoal.getName());
                    goal.setImportance(editedGoal.getImportance());
                    return goalRepository.save(goal);
                })
                .orElseThrow(() -> new GoalNotFoundException(id));
    }
}

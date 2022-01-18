package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserEntityRepository userRepository;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public GoalService(GoalRepository goalRepository, UserEntityRepository userRepository, AuthenticationFacade authenticationFacade) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public List<Goal> all() {
        String username = authenticationFacade.getAuthentication().getName();
        try {
            UserEntity user = userRepository.findByEmailAddress(username);
            return goalRepository.findByUserId(user.getId());
        } catch (UserEntityNotFoundException e) {
            throw new UserEntityNotFoundException(username);
        }
    }

    public Goal one(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    public Goal newGoal(Goal goal) {
        String username = authenticationFacade.getAuthentication().getName();
        try {
            UserEntity user = userRepository.findByEmailAddress(username);
            return goalRepository.save(new Goal(goal.getName(),
                    goal.getImportance(),
                    goal.getProgressPercentage(),
                    user));
        } catch (UserEntityNotFoundException e) {
            throw new UserEntityNotFoundException(username);
        }
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

package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotFoundException;
import me.projects.SelfOKRs.pojos.GoalRequest;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    public GoalService() {}

    public List<Goal> all() {
        return goalRepository.findAll();
    }

    public Goal one(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new GoalNotFoundException(id));
    }

    public Goal newGoal(GoalRequest goalRequest) {
        User user = userRepository.findById(goalRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException(goalRequest.getUserId()));
        return goalRepository.save(new Goal(goalRequest.getName(),
                goalRequest.getImportance(),
                goalRequest.getProgressPercentage(),
                user));
    }

    public void deleteOne(Long id) {
        goalRepository.deleteById(id);
    }
}

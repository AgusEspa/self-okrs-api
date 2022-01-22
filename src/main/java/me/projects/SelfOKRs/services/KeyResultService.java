package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.KeyResultRequest;
import me.projects.SelfOKRs.entities.Goal;
import me.projects.SelfOKRs.entities.KeyResult;
import me.projects.SelfOKRs.exceptions.GoalNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.GoalRepository;
import me.projects.SelfOKRs.repositories.KeyResultRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyResultService {

    @Autowired
    private final KeyResultRepository keyResultRepository;

    private final UserEntityRepository userRepository;

    private final GoalRepository goalRepository;

    private final AuthenticationFacade authenticationFacade;

    Logger logger = LoggerFactory.getLogger(KeyResultService.class);

    @Autowired
    public KeyResultService(KeyResultRepository repository, UserEntityRepository userRepository, GoalRepository goalRepository, AuthenticationFacade authenticationFacade) {
        this.keyResultRepository = repository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.authenticationFacade = authenticationFacade;
    }


    public List<KeyResult> allPerGoal(Long goalId) {
        String username = authenticationFacade.getAuthentication().getName();
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new GoalNotFoundException(goalId));
        if (goal.getUser().getEmailAddress().equals(username)) {
            return keyResultRepository.findAllPerGoal(goalId);
        } else throw new UserEntityNotFoundException(username);

    }

    public KeyResult newKeyResult(KeyResultRequest keyResultRequest) {

        Goal goal = goalRepository.getById(keyResultRequest.getGoalId());

        return keyResultRepository.save(new KeyResult(keyResultRequest.getName(), goal));
    }

}

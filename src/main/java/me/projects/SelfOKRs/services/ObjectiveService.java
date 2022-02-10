package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.ObjectiveRequest;
import me.projects.SelfOKRs.entities.Objective;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.ObjectiveNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.ObjectiveRepository;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    private final UserEntityRepository userEntityRepository;

    Logger logger = LoggerFactory.getLogger(ObjectiveService.class);


    @Autowired
    public ObjectiveService(ObjectiveRepository objectiveRepository, UserEntityRepository userEntityRepository) {
        this.objectiveRepository = objectiveRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public List<Objective> fetchObjectives() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return objectiveRepository.findByUserId(user.getId());
    }

    // Refactor, not secure
//    public Objective one(Long id) {
//        return objectiveRepository.findById(id)
//                .orElseThrow(() -> new ObjectiveNotFoundException(id));
//    }

    public Objective newObjective(ObjectiveRequest objectiveRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return objectiveRepository.save(new Objective(objectiveRequest.getName(),
                objectiveRequest.getImportance(),
                user));
    }

    // Refactor, not secure
    public void removeObjective(Long id) {
        objectiveRepository.deleteById(id);
    }

    // Refactor, not secure
    public Objective updateObjective(Long id, Objective editedObjective) {
        return objectiveRepository.findById(id)
                .map(objective -> {
                    objective.setName(editedObjective.getName());
                    objective.setImportance(editedObjective.getImportance());
                    return objectiveRepository.save(objective);
                })
                .orElseThrow(() -> new ObjectiveNotFoundException(id));
    }
}

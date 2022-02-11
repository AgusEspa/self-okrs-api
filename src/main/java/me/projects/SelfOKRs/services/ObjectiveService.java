package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.ObjectiveRequest;
import me.projects.SelfOKRs.entities.Objective;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.ObjectiveNotFoundException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
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

        String username = getUsername();

        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return objectiveRepository.findByUserId(user.getId());
    }

    public Objective newObjective(ObjectiveRequest objectiveRequest) {

        String username = getUsername();

        UserEntity user = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        return objectiveRepository.save(new Objective(objectiveRequest.getName(),
                objectiveRequest.getImportance(),
                user));
    }

    public Objective updateObjective(Long id, ObjectiveRequest editedObjective) {

        Objective fetchedObjective = validateUserAndFetchObjective(id);

        fetchedObjective.setName(editedObjective.getName());
        fetchedObjective.setImportance(editedObjective.getImportance());
        return objectiveRepository.save(fetchedObjective);
    }

    public void removeObjective(Long id) {

        validateUserAndFetchObjective(id);
        objectiveRepository.deleteById(id);
    }

    protected Objective validateUserAndFetchObjective(Long id) {

        String username = getUsername();

        Objective fetchedObjective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException(id));

        if (!fetchedObjective.getUser().getEmailAddress().equals(username)) throw new UserNotAuthorizedException(username);
        else return fetchedObjective;
    }

    protected String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

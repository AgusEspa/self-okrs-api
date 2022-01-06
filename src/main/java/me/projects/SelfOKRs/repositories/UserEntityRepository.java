package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByEmailAddress(String emailAddress);
}

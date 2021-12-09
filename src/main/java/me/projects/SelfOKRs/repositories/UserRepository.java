package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

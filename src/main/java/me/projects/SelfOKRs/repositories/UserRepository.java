package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

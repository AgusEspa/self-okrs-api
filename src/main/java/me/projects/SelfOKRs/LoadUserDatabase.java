package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class LoadUserDatabase implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public LoadUserDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        userRepository.save(new User("Coco Chanel", "cocoto@gmail.com", "jshfkdsjhfksjdf"));
    }

}

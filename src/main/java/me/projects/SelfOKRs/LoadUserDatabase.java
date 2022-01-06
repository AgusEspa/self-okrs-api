package me.projects.SelfOKRs;

import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class LoadUserDatabase implements CommandLineRunner {

    private final UserEntityRepository userRepository;

    @Autowired
    public LoadUserDatabase(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
//        userRepository.save(new User("Mike Langdon", "penisxl@gmail.com", "5345jg84hgsdfg=M"));
    }

}

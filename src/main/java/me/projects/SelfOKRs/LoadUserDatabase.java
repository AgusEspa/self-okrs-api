package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class LoadUserDatabase implements CommandLineRunner {

    private final UserEntityRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoadUserDatabase(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {
        RegistrationForm registrationForm = new RegistrationForm("Admin", "admin@mail.com", "5345jg84hgsdfg=M");
        UserEntity user = registrationForm.toUser(passwordEncoder);
        userRepository.save(user);
    }

}

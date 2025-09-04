package Capstone.capstone_project.runners;

import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.enums.Roles;
import Capstone.capstone_project.repositories.UserRepository;
import com.cloudinary.provisioning.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class AdminCreation {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createAdminIfNeeded() {
        String adminEmail = "administrator@hotmail.it";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setNome("Renato");
            admin.setCognome("Morelli");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRuolo(Roles.ADMIN);

            userRepository.save(admin);
            System.out.println("Admin creato con successo (email: " + adminEmail + ")");
        } else {
            System.out.println("Admin già esistente, nessuna azione necessaria.");
        }
    }
}

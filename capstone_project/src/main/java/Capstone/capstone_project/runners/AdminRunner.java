package Capstone.capstone_project.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminRunner implements CommandLineRunner {

    @Autowired
    private AdminCreation adminCreation;
    @Override
    public void run(String... args) throws Exception {
        adminCreation.createAdminIfNeeded();


    }
}

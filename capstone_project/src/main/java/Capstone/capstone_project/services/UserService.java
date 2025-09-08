package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.enums.Roles;
import Capstone.capstone_project.exceptions.BadRequestException;
import Capstone.capstone_project.payloads.NewUserDTO;
import Capstone.capstone_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findAll(int page, int size, String sortBy) {
        return usersRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public User findById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Utente non trovato"));
    }

    public Page <User> findAll(Pageable pageable){
        return usersRepository.findAll(pageable);
    }

    public User findByIdAndUpdate(Long userId, NewUserDTO payload) {
        User found = this.findById(userId);

        if (!found.getEmail().equals(payload.email())) {
            usersRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });
        }

        found.setEmail(payload.email());
        found.setPassword(passwordEncoder.encode(payload.password()));

        User modifiedUser = usersRepository.save(found);
        return modifiedUser;
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Utente non trovato con email: " + email));
    }

    public void delete(Long userId) {
        User user = findById(userId);
        usersRepository.delete(user);
    }

    public User save(NewUserDTO payload) {
        // Controllo email duplicata
        usersRepository.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        String encodedPassword = passwordEncoder.encode(payload.password());
        User newUser = new User(payload.nome(), payload.cognome(), payload.email(), encodedPassword, Roles.UTENTE);
        return usersRepository.save(newUser);
    }
}
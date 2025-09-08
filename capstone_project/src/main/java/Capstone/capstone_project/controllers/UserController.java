package Capstone.capstone_project.controllers;


import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.payloads.NewUserDTO;
import Capstone.capstone_project.payloads.NewUserResponseDTO;
import Capstone.capstone_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UserController {

    @Autowired
    private UserService utentiService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<NewUserResponseDTO> getAllUsers(@RequestParam (value = "page", defaultValue = "0")int page, @RequestParam(value = "size", defaultValue = "10")int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page <User> usersPage = this.utentiService.findAll(pageable);
        return usersPage.stream().map(user -> new NewUserResponseDTO(
                user.getId()
        )).toList();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Long id) {
        this.utentiService.delete(id);
    }


    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User update(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated NewUserDTO payload) {
        return this.utentiService.findByIdAndUpdate(currentAuthenticatedUser.getId(), payload);
    }
}

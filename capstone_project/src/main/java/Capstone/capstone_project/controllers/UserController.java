package Capstone.capstone_project.controllers;


import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.payloads.NewUserDTO;
import Capstone.capstone_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UserController {

    @Autowired
    private UserService utentiService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@RequestParam Long id) {
        return this.utentiService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or principal.username == @userService.getUsernameById(#autoreId)")
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

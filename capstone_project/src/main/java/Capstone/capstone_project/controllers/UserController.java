package Capstone.capstone_project.controllers;


import Capstone.capstone_project.entities.User;
import Ren_Mor.gestione_eventi.payloads.NewUserDTO;
import Ren_Mor.gestione_eventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UserController {

    @Autowired
    private UserService utentiService;

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User updateOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody @Validated NewUserDTO payload) {
        return this.utentiService.findByIdAndUpdate(currentAuthenticatedUser.getId(), payload);
    }
}

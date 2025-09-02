package Capstone.capstone_project.controllers;

import Ren_Mor.gestione_eventi.entities.User;
import Ren_Mor.gestione_eventi.exceptions.ValidationException;
import Ren_Mor.gestione_eventi.payloads.LoginDTO;
import Ren_Mor.gestione_eventi.payloads.LoginResponseDTO;
import Ren_Mor.gestione_eventi.payloads.NewUserDTO;
import Ren_Mor.gestione_eventi.payloads.NewUserResponseDTO;
import Ren_Mor.gestione_eventi.services.AuthService;
import Ren_Mor.gestione_eventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService utentiService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        String accessToken = authService.checkCredentialsAndGenerateToken(body);
        return new LoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO save(@RequestBody @Validated NewUserDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            User newUser = this.utentiService.save(payload);
            return new NewUserResponseDTO(newUser.getId());
        }
    }
}

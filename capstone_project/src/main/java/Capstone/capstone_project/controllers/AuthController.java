package Capstone.capstone_project.controllers;


import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.exceptions.ValidationException;
import Capstone.capstone_project.payloads.LoginDTO;
import Capstone.capstone_project.payloads.LoginResponseDTO;
import Capstone.capstone_project.payloads.NewUserDTO;
import Capstone.capstone_project.payloads.NewUserResponseDTO;
import Capstone.capstone_project.services.AuthService;
import Capstone.capstone_project.services.UserService;
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
            return new NewUserResponseDTO(
                    newUser.getId(),
                    newUser.getNome(),
                    newUser.getCognome(),
                    newUser.getEmail(),
                    newUser.getRuolo()
            );
        }
    }
}

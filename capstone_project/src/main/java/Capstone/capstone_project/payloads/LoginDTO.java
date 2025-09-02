package Capstone.capstone_project.payloads;


import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "E-mail  obbligatorio.")
        String email,
        @NotEmpty(message = "Password obbligatoria.")
        String password
) {}

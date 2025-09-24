package Capstone.capstone_project.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cognome,
        @NotBlank(message = "L'email è obbligatoria.")
        String email,
        @NotBlank(message = "La password è obbligatoria.")
        @Size(min = 4)
        String password){
}
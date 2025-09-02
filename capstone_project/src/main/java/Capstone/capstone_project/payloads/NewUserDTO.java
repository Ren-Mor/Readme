package Capstone.capstone_project.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cognome,
        @NotEmpty(message = "Il nome è obbligatorio.")
        @Size(min = 2, max = 25, message = "Il nome deve essere di lunghezza compresa tra 2 e 25")
        String username,
        @NotBlank(message = "L'email è obbligatoria.")
        String email,
        @NotBlank(message = "La password è obbligatoria.")
        @Size(min = 4)
        String password) {
}

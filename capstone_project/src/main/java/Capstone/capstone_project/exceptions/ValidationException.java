package Capstone.capstone_project.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

    private List<String> errorMessages;

    public ValidationException(List<String> errorMessages) {
        super("Errore di validazione.");
        this.errorMessages = errorMessages;
    }

    // Getter
    public List<String> getErrorMessages() {
        return errorMessages;
    }
}

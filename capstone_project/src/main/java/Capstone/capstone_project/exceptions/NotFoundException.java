package Capstone.capstone_project.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id) {
        super("L'elemento' con id " + id + " non è stata trovato.");
    }
}

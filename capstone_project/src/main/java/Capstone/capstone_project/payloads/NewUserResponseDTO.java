package Capstone.capstone_project.payloads;


import Capstone.capstone_project.enums.Roles;

public record NewUserResponseDTO(
        Long id,
        String nome,
        String cognome,
        String email,
        Roles ruolo
) {}

package Capstone.capstone_project.payloads;

public record AddressDTO(
        Long id,
        String via,
        String citta,
        String provincia,
        String cap,
        Long utenteId
) {}
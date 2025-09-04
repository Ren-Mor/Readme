package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.Ordine;
import Capstone.capstone_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByUtente(User utente);
    Optional<Ordine> findByNumeroOrdine(String numeroOrdine);
}
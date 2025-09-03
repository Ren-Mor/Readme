package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.Cart;
import Capstone.capstone_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUtente(User utente);
}

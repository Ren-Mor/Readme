package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.Payment;
import Capstone.capstone_project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrdine(Order ordine);
}

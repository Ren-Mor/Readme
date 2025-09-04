package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.OrderItem;
import Capstone.capstone_project.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrdine(Ordine ordine);
}

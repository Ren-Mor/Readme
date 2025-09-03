package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.CartItem;
import Capstone.capstone_project.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCarrello(Cart carrello);
}
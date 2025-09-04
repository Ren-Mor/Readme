package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.*;
import Capstone.capstone_project.enums.OrderStatus;
import Capstone.capstone_project.exceptions.NotFoundException;
import Capstone.capstone_project.repositories.OrderItemRepository;
import Capstone.capstone_project.repositories.OrderRepository;
import Capstone.capstone_project.repositories.ProductRepository;
import Capstone.capstone_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Ordine> getOrdersByUser(User utente) {
        return orderRepository.findByUtente(utente);
    }

    public Ordine getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.intValue()));
    }

    @Transactional
    public Ordine createOrder(User utente, List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Il carrello è vuoto.");
        }

        double totale = cartItems.stream()
                .mapToDouble(item -> item.getProdotto().getPrezzo() * item.getQuantita())
                .sum();

        Ordine ordine = new Ordine();
        ordine.setNumeroOrdine(UUID.randomUUID().toString());
        ordine.setDataOrdine(LocalDateTime.now());
        ordine.setTotale(totale);
        ordine.setStatoOrdine(OrderStatus.PENDING);
        ordine.setUtente(utente);

        ordine = orderRepository.save(ordine);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(
                    ordine,
                    cartItem.getProdotto(),
                    cartItem.getQuantita(),
                    cartItem.getProdotto().getPrezzo()
            );
            orderItemRepository.save(orderItem);
        }

        // Aggiorna la lista degli item dell'ordine
        ordine.setItems(orderItemRepository.findByOrdine(ordine));
        return orderRepository.save(ordine);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Ordine ordine = getOrderById(orderId);
        ordine.setStatoOrdine(status);
        orderRepository.save(ordine);
    }

    public void deleteOrder(Long orderId) {
        Ordine ordine = getOrderById(orderId);
        orderRepository.delete(ordine);
    }
}
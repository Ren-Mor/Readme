package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Cart;
import Capstone.capstone_project.entities.Order;
import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.enums.OrderStatus;
import Capstone.capstone_project.services.CartService;
import Capstone.capstone_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    // Lista ordini dell'utente autenticato
    @GetMapping
    public List<Order> getMyOrders(@AuthenticationPrincipal User currentUser) {
        return orderService.getOrdersByUser(currentUser);
    }

    // Dettaglio ordine
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        Order ordine = orderService.getOrderById(id);
        if (!ordine.getUtente().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Non autorizzato a visualizzare questo ordine.");
        }
        return ordine;
    }

    // Creazione ordine dal carrello
    @PostMapping
    public Order createOrder(@AuthenticationPrincipal User currentUser) {
        Cart cart = cartService.getCartByUser(currentUser);
        Order order = orderService.createOrder(currentUser, cart.getItems());
        cartService.clearCart(currentUser);
        return order;
    }

    // Aggiornamento stato ordine (solo ADMIN)
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
    }

    // Cancellazione ordine (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
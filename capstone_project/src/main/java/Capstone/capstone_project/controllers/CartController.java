package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Cart;
import Capstone.capstone_project.entities.User;
import Capstone.capstone_project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Visualizzo il carrello dell'utente autenticato
    @GetMapping
    public Cart getCart(@AuthenticationPrincipal User currentUser) {
        return cartService.getCartByUser(currentUser);
    }

    // Aggiungo un prodotto al carrello
    @PostMapping("/add")
    public Cart addProductToCart(
            @AuthenticationPrincipal User currentUser,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantita
    ) {
        return cartService.addProductToCart(currentUser, productId, quantita);
    }

    // Rimuovo un prodotto dal carrello
    @DeleteMapping("/remove")
    public Cart removeProductFromCart(
            @AuthenticationPrincipal User currentUser,
            @RequestParam Long productId
    ) {
        return cartService.removeProductFromCart(currentUser, productId);
    }

    // Svuoto il carrello
    @DeleteMapping("/clear")
    public Cart clearCart(@AuthenticationPrincipal User currentUser) {
        return cartService.clearCart(currentUser);
    }

    // Calcolo totale carrello
    @GetMapping("/total")
    public Double getCartTotal(@AuthenticationPrincipal User currentUser) {
        Cart cart = cartService.getCartByUser(currentUser);
        return cartService.calculateTotal(cart);
    }

}
package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.*;
import Capstone.capstone_project.exceptions.NotFoundException;
import Capstone.capstone_project.repositories.CartItemRepository;
import Capstone.capstone_project.repositories.CartRepository;
import Capstone.capstone_project.repositories.ProductRepository;
import Capstone.capstone_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Cart getCartByUser(User utente) {
        return cartRepository.findByUtente(utente)
                .orElseGet(() -> cartRepository.save(new Cart(utente, List.of())));
    }

    @Transactional
    public Cart addProductToCart(User utente, Long productId, int quantita) {
        Cart cart = getCartByUser(utente);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(productId.intValue()));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProdotto().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantita(item.getQuantita() + quantita);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(cart, product, quantita);
            cart.getItems().add(cartItemRepository.save(newItem));
        }
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeProductFromCart(User utente, Long productId) {
        Cart cart = getCartByUser(utente);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProdotto().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(productId.intValue()));

        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart clearCart(User utente) {
        Cart cart = getCartByUser(utente);
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

    public double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(i -> i.getProdotto().getPrezzo() * i.getQuantita())
                .sum();
    }
}
package Capstone.capstone_project.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public Cart() {}

    public Cart(User utente, List<CartItem> items) {
        this.utente = utente;
        this.items = items;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public User getUtente() {
        return utente;
    }

    // Setter
    public void setUtente(User utente) {
        this.utente = utente;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}

package Capstone.capstone_project.entities;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product prodotto;

    private int quantita;

    public CartItem() {}

    public CartItem(Product prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Product getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    // Setter
    public void setProdotto(Product prodotto) {
        this.prodotto = prodotto;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
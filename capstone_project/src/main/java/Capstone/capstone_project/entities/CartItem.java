package Capstone.capstone_project.entities;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart carrello;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product prodotto;

    private int quantita;

    public CartItem() {}

    public CartItem(Cart carrello, Product prodotto, int quantita) {
        this.carrello = carrello;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Cart getCarrello() {
        return carrello;
    }

    public Product getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    // Setter
    public void setCarrello(Cart carrello) {
        this.carrello = carrello;
    }

    public void setProdotto(Product prodotto) {
        this.prodotto = prodotto;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}

package Capstone.capstone_project.entities;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order ordine;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product prodotto;

    private int quantita;
    private double prezzoUnitario;

    public OrderItem() {}

    public OrderItem(Order ordine, Product prodotto, int quantita, double prezzoUnitario) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public Order getOrdine() {
        return ordine;
    }

    public Product getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    // Setter
    public void setOrdine(Order ordine) {
        this.ordine = ordine;
    }

    public void setProdotto(Product prodotto) {
        this.prodotto = prodotto;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }
}

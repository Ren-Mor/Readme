package Capstone.capstone_project.entities;

import Capstone.capstone_project.enums.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroOrdine;

    private LocalDateTime dataOrdine;
    private Double totale;

    @Enumerated(EnumType.STRING)
    private OrderStatus statoOrdine;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {}

    public Order(String numeroOrdine, LocalDateTime dataOrdine, Double totale, OrderStatus statoOrdine, User utente, List<OrderItem> items) {
        this.numeroOrdine = numeroOrdine;
        this.dataOrdine = dataOrdine;
        this.totale = totale;
        this.statoOrdine = statoOrdine;
        this.utente = utente;
        this.items = items;
    }

    // Getter
    public String getNumeroOrdine() {
        return numeroOrdine;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public Double getTotale() {
        return totale;
    }

    public OrderStatus getStatoOrdine() {
        return statoOrdine;
    }

    public User getUtente() {
        return utente;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    // Setter
    public void setNumeroOrdine(String numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public void setStatoOrdine(OrderStatus statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

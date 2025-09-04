package Capstone.capstone_project.entities;

import Capstone.capstone_project.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodo;
    private Double importo;

    @Enumerated(EnumType.STRING)
    private PaymentStatus statoPagamento;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Ordine ordine;

    public Payment() {}

    public Payment(String metodo, Double importo, PaymentStatus statoPagamento, Ordine ordine) {
        this.metodo = metodo;
        this.importo = importo;
        this.statoPagamento = statoPagamento;
        this.ordine = ordine;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getMetodo() {
        return metodo;
    }

    public Double getImporto() {
        return importo;
    }

    public PaymentStatus getStatoPagamento() {
        return statoPagamento;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    // Setter
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public void setStatoPagamento(PaymentStatus statoPagamento) {
        this.statoPagamento = statoPagamento;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
}

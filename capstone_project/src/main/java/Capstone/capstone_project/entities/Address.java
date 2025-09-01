package Capstone.capstone_project.entities;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String via;
    private String citta;
    private String provincia;
    private String cap;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utente;

    public Address() {}

    public Address(String via, String citta, String provincia, String cap, User utente) {
        this.via = via;
        this.citta = citta;
        this.provincia = provincia;
        this.cap = cap;
        this.utente = utente;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCap() {
        return cap;
    }

    public User getUtente() {
        return utente;
    }

    // Setter
    public void setVia(String via) {
        this.via = via;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }
}

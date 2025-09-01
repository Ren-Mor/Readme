package Capstone.capstone_project.entities;

import Capstone.capstone_project.enums.Category;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private Double prezzo;
    private String immagine;
    private Category categoria;



    public Product() {}

    public Product(String nome, String descrizione, Double prezzo, String immagine, Category categoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        this.categoria = categoria;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public String getImmagine() {
        return immagine;
    }

    public Category getCategoria() {
        return categoria;
    }

    // Setter
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }
}

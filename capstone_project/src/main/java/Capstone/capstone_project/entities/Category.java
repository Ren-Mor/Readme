package Capstone.capstone_project.entities;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public Category() {}

    public Category(String nome) {
        this.nome = nome;
    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Setter
    public void setNome(String nome) {
        this.nome = nome;
    }
}

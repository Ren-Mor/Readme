package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNomeContainingIgnoreCase(String nome);
    List<Product> findByCategoria(Category categoria);
}

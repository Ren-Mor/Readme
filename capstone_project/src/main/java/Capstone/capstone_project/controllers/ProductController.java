package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import Capstone.capstone_project.payloads.NewProductDTO;
import Capstone.capstone_project.payloads.ProductDTO;
import Capstone.capstone_project.payloads.UpdateProductDTO;
import Capstone.capstone_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Lista paginata di prodotti (pubblica)
    @GetMapping ("/all")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    // Filtro per categoria (pubblica)
    @GetMapping("/category/{categoria}")
    public List<Product> getByCategoria(@PathVariable Category categoria) {
        return productService.findByCategoria(categoria);
    }

    // Dettaglio prodotto (pubblica)
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // --- BACKOFFICE ---


    // Creazione prodotto (solo ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product create(@RequestBody @Validated NewProductDTO payload) {
        return productService.save(payload);
    }

    // Modifica prodotto (solo ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product update(@PathVariable Long id, @RequestBody @Validated UpdateProductDTO payload) {
        return productService.update(id, payload);
    }

    // Cancellazione prodotto (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
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
    @GetMapping
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return productService.findAll(page, size, sortBy);
    }

    // Ricerca per nome (pubblica)
    @GetMapping("/search")
    public List<Product> searchByNome(@RequestParam String nome) {
        return productService.searchByNome(nome);
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

    // Lista completa prodotti (paginata, solo ADMIN)
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Product> getAllProductsForAdmin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return productService.findAll(page, size, sortBy);
    }

    // Esporta tutti i prodotti (solo ADMIN)
    @GetMapping("/admin/export")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> exportAllProducts() {
        return productService.findAll(0, Integer.MAX_VALUE, "id").getContent();
    }

    // Statistiche prodotti (esempio: numero totale prodotti per categoria, solo ADMIN)
    @GetMapping("/admin/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<Category, Long> getProductStats() {
        // Esempio: conta prodotti per categoria
        List<Product> all = productService.findAll(0, Integer.MAX_VALUE, "id").getContent();
        return all.stream().collect(java.util.stream.Collectors.groupingBy(Product::getCategoria, java.util.stream.Collectors.counting()));
    }

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
package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import Capstone.capstone_project.payloads.NewProductDTO;
import Capstone.capstone_project.payloads.ProductDTO;
import Capstone.capstone_project.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Lista paginata di prodotti (pubblica)
    @GetMapping ("category/all")
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Product create(
            @RequestPart("dto") @Valid NewProductDTO dto,
            @RequestPart("image") MultipartFile image)
             {
        return productService.save(dto, image);
    }

    // Modifica immagine prodotto (solo ADMIN)
    @PostMapping("/image/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, String> uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) {

        return productService.updateImage(id, image );
    }

    // Aggiorna solo i dati (solo ADMIN)
    @PutMapping("/data/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public NewProductDTO updateData(
            @PathVariable Long id,
            @Valid @RequestBody NewProductDTO payload) {

        Product updatedProduct = productService.updateData(id, payload);

        return new NewProductDTO(
                updatedProduct.getNome(),
                updatedProduct.getDescrizione(),
                updatedProduct.getPrezzo(),
                updatedProduct.getCategoria()
        );
    }

    // Cancellazione prodotto (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
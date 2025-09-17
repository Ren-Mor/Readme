package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import Capstone.capstone_project.payloads.NewProductDTO;
import Capstone.capstone_project.payloads.ProductDTO;
import Capstone.capstone_project.payloads.UpdateProductDTO;
import Capstone.capstone_project.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Product create(
            @RequestPart("dto") @Valid NewProductDTO dto,
            @RequestPart("image") MultipartFile image)
             {
        return productService.save(dto, image);
    }

    // Modifica prodotto (solo ADMIN)
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public Product update(@PathVariable Long id,
                          @RequestPart("dto") UpdateProductDTO dtoRequestPart, @RequestPart(name = "image", required = false) MultipartFile image)
    {UpdateProductDTO dto = new UpdateProductDTO(
                dtoRequestPart.nome(),
                dtoRequestPart.descrizione(),
                dtoRequestPart.prezzo(),
                image,
                dtoRequestPart.categoria()

        );

        return productService.update(id, dto);
    }

    // Cancellazione prodotto (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
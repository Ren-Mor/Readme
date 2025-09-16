package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import Capstone.capstone_project.exceptions.NotFoundException;
import Capstone.capstone_project.payloads.NewProductDTO;
import Capstone.capstone_project.payloads.ProductDTO;
import Capstone.capstone_project.payloads.UpdateProductDTO;
import Capstone.capstone_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List <Product> products = productRepository.findAll();
        List <ProductDTO> results = new ArrayList<>();
        for (Product p : products) {
            ProductDTO dto = new ProductDTO(
                    p.getId(),
                    p.getNome(),
                    p.getDescrizione(),
                    p.getPrezzo(),
                    p.getImmagine(),
                    p.getCategoria()
            );
            results.add(dto);
        }
        return results;
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id.intValue()));
    }


    public List<Product> findByCategoria(Category categoria) {
        return productRepository.findByCategoria(categoria);
    }

    public Product save(NewProductDTO payload) {
        Product product = new Product(
                payload.nome(),
                payload.descrizione(),
                payload.prezzo(),
                payload.immagine(),
                payload.categoria()
        );
        return productRepository.save(product);
    }

    public Product update(Long id, UpdateProductDTO payload) {
        Product product = findById(id);
        product.setNome(payload.nome());
        product.setDescrizione(payload.descrizione());
        product.setPrezzo(payload.prezzo());
        product.setImmagine(payload.immagine());
        product.setCategoria(payload.categoria());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
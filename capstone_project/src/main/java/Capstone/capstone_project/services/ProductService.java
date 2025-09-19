package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.Product;
import Capstone.capstone_project.enums.Category;
import Capstone.capstone_project.exceptions.BadRequestException;
import Capstone.capstone_project.exceptions.NotFoundException;
import Capstone.capstone_project.payloads.NewProductDTO;
import Capstone.capstone_project.payloads.ProductDTO;
import Capstone.capstone_project.repositories.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

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

    public Product save(NewProductDTO payload, MultipartFile imageFile) {
        String imageUrl;
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            imageUrl = uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento dell'immagine su Cloudinary", e);
        }
        Product product = new Product(
                payload.nome(),
                payload.descrizione(),
                payload.prezzo(),
                imageUrl,
                payload.categoria()
        );
        return productRepository.save(product);
    }

    public Map<String, String> updateImage(Long id, MultipartFile file) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        try {
            String imageUrl = (String) cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            product.setImmagine(product.getImmagine());
            productRepository.save(product);

            return Map.of("imageUrl", imageUrl);

        } catch (IOException e) {
            throw new BadRequestException("Errore durante il caricamento dell'immagine");
        }
    }

    public Product updateData(Long id, NewProductDTO payload) {
        Product product =productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        if (payload.nome() != null) product.setNome(payload.nome());
        if (payload.descrizione() != null) product.setDescrizione(payload.descrizione());
        if (payload.prezzo() != null) product.setPrezzo(payload.prezzo());
        if (payload.categoria() != null) product.setCategoria(payload.categoria());

        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
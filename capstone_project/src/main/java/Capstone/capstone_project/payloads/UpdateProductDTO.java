package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.Category;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProductDTO(
         String nome,
         String descrizione,
         Double prezzo,
         MultipartFile immagine,
         Category categoria
) {}

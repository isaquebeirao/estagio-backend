package desafio.api.backend.service;



import desafio.api.backend.model.Product;

import desafio.api.backend.model.dto.RegisterProductDTO;
import desafio.api.backend.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public ResponseEntity createProduct (RegisterProductDTO product) {
        productRepository.save(new Product(product));
        return ResponseEntity.ok().build();
    }


}

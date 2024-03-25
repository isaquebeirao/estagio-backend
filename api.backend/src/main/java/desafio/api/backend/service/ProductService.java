package desafio.api.backend.service;



import desafio.api.backend.model.Product;

import desafio.api.backend.model.dto.ListProductDTO;
import desafio.api.backend.model.dto.RegisterProductDTO;
import desafio.api.backend.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public ResponseEntity createProduct (RegisterProductDTO product) {
        productRepository.save(new Product(product));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<ListProductDTO>> listProduct () {
        List<ListProductDTO> list = productRepository.findAll().stream().map(ListProductDTO::new).toList();
        return ResponseEntity.ok(list);
    }

}

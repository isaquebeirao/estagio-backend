package desafio.api.backend.controller;

import desafio.api.backend.model.dto.RegisterProductDTO;
import desafio.api.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct (@RequestBody @Valid RegisterProductDTO product) {
        return productService.createProduct(product);
    }

}

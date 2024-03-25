package desafio.api.backend.controller;

import desafio.api.backend.model.dto.ListProductDTO;
import desafio.api.backend.model.dto.RegisterProductDTO;
import desafio.api.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct (@RequestBody @Valid RegisterProductDTO product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<ListProductDTO>> listProduct () {
        return productService.listProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity selectProduct(@PathVariable Long id) {
        return productService.selectProduct(id);
    }

}

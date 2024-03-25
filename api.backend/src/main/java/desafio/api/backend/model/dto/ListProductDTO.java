package desafio.api.backend.model.dto;

import desafio.api.backend.model.Product;

public record ListProductDTO(
        Long id,
        String name,
        String description,
        Double value) {
    public ListProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getValue());
    }
}

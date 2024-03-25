package desafio.api.backend.model.dto;

public record RegisterProductDTO(
        String name,
        String description,
        Double value) {
}

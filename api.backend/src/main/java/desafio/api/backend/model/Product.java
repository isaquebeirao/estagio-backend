package desafio.api.backend.model;


import desafio.api.backend.model.dto.RegisterProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double value;

    public Product (RegisterProductDTO product) {
        this.name = product.name();
        this.description = product.description();
        this.value = product.value();
    }

}

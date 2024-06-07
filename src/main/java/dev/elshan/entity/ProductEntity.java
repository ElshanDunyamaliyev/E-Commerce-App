package dev.elshan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    @Column(name = "product_name")
    private String productName;

    private String image;

    @NotBlank
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;

    private Integer quantity;
    private double price;
    private double discount;
}

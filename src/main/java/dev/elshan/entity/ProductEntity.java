package dev.elshan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    private String image;
    private String description;
    private Integer quantity;
    private double price;
    private double discountPercent;
    private double discountPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;
}

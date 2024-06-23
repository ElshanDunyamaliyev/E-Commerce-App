package dev.elshan.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    @NotBlank(message = "Category must have name")
    @Size(min = 5, max = 50,message = "Category name size must be between 5 and 50")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade =  CascadeType.ALL )
    private List<ProductEntity> products;

}
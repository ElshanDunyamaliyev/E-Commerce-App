package dev.elshan.repository;

import dev.elshan.entity.CategoryEntity;
import dev.elshan.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findProductEntityByCategoryOrderByPriceAsc(CategoryEntity category);
}

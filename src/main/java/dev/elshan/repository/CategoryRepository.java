package dev.elshan.repository;

import dev.elshan.entity.CategoryEntity;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Category findByCategoryName(String categoryName);
}

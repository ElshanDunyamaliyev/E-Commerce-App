package dev.elshan.service;

import dev.elshan.dto.CategoryDto;
import dev.elshan.dto.ProductDto;
import dev.elshan.entity.CategoryEntity;
import dev.elshan.entity.ProductEntity;
import dev.elshan.mapper.CategoryMapper;
import dev.elshan.mapper.ProductMapper;
import dev.elshan.repository.CategoryRepository;
import dev.elshan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::mapToDto).toList();
    }

    public CategoryDto getCategoryById(Long categoryId) {
        CategoryEntity productEntity =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return categoryMapper.mapToDto(productEntity);
    }

    public void createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapToEntity(categoryDto);
        categoryRepository.save(categoryEntity);
    }

    public void updateCategory(Long categoryId,CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow();
        categoryEntity.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}

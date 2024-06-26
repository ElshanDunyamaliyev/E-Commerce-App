package dev.elshan.service;

import dev.elshan.dto.CategoryDto;
import dev.elshan.dto.ProductDto;
import dev.elshan.entity.CategoryEntity;
import dev.elshan.entity.ProductEntity;
import dev.elshan.exception.ApiException;
import dev.elshan.exception.ResourceNotFoundException;
import dev.elshan.mapper.CategoryMapper;
import dev.elshan.mapper.ProductMapper;
import dev.elshan.repository.CategoryRepository;
import dev.elshan.repository.ProductRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories(Integer pageNumber, Integer pageSize,
                                              String sortOrder,String sortBy) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sortByAndOrder);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryEntity> categories = categoryPage.getContent();
        return categories.stream().map(categoryMapper::mapToDto).toList();
    }

    public CategoryDto getCategoryById(Long categoryId) {
        CategoryEntity productEntity =  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category",categoryId));
        return categoryMapper.mapToDto(productEntity);
    }

    public void createCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if(category != null){
            throw new ApiException("Category with this name exists: " + categoryDto.getCategoryName());
        }
        CategoryEntity categoryEntity = categoryMapper.mapToEntity(categoryDto);
        categoryRepository.save(categoryEntity);
    }

    public void updateCategory(Long categoryId,CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category",categoryId));
        categoryEntity.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}

package dev.elshan.service;

import dev.elshan.dto.ProductDto;
import dev.elshan.entity.CategoryEntity;
import dev.elshan.entity.ProductEntity;
import dev.elshan.mapper.ProductMapper;
import dev.elshan.repository.CategoryRepository;
import dev.elshan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
    }

    public ProductDto getProductById(Long productId) {
        ProductEntity productEntity =  productRepository.findById(productId).orElseThrow();
        return productMapper.mapToDto(productEntity);
    }

    public void createProduct(Long categoryId,ProductDto productDto) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow();
        ProductEntity product = productMapper.mapToEntity(productDto);
        product.setCategory(category);
        category.getProducts().add(product);
        productRepository.save(product);
        categoryRepository.save(category);
    }

    public void updateProduct(Long productId,ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        productEntity.setProductName(productDto.getProductName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setQuantity(productDto.getQuantity());
        productEntity.setDiscount(productDto.getDiscount());
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

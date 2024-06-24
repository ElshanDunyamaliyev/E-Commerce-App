package dev.elshan.service;

import dev.elshan.dto.ProductDto;
import dev.elshan.entity.CategoryEntity;
import dev.elshan.entity.ProductEntity;
import dev.elshan.exception.ResourceNotFoundException;
import dev.elshan.mapper.ProductMapper;
import dev.elshan.repository.CategoryRepository;
import dev.elshan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
    }

    public List<ProductDto> getProductsByCategory(Long categoryId){
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));
        List<ProductEntity> productEntity = productRepository.findProductEntityByCategoryOrderByPriceAsc(category);
        List<ProductDto> productDtos = productEntity.stream().map(productMapper::mapToDto).collect(Collectors.toList());
        return productDtos;
    }

    public ProductDto getProductById(Long productId) {
        ProductEntity productEntity =  productRepository.findById(productId).orElseThrow();
        return productMapper.mapToDto(productEntity);
    }

    public void createProduct(Long categoryId,ProductDto productDto) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow();
        ProductEntity productEntity = productMapper.mapToEntity(productDto);
        double priceWithDiscountPercent = productEntity.getPrice() - productEntity.getPrice() * productEntity.getDiscountPercent() * 0.01;
        productEntity.setDiscountPrice(priceWithDiscountPercent);
        productEntity.setImage("default.png");
        productEntity.setCategory(category);
        category.getProducts().add(productEntity);
        productRepository.save(productEntity);
        categoryRepository.save(category);
    }

    public void updateProduct(Long productId,ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        productEntity.setProductName(productDto.getProductName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setQuantity(productDto.getQuantity());
        productEntity.setDiscountPercent(productDto.getDiscountPercent());
        double priceWithDiscountPercent = productEntity.getPrice() - productEntity.getPrice() * productEntity.getDiscountPercent() * 0.01;
        productEntity.setDiscountPrice(priceWithDiscountPercent);
        productRepository.save(productEntity);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

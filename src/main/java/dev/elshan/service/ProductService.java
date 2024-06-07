package dev.elshan.service;

import dev.elshan.dto.ProductDto;
import dev.elshan.entity.ProductEntity;
import dev.elshan.mapper.ProductMapper;
import dev.elshan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
    }

    public ProductDto getProductById(Long productId) {
        ProductEntity productEntity =  productRepository.findById(productId).orElseThrow();
        return productMapper.mapToDto(productEntity);
    }

    public void createProduct(ProductDto productDto) {
        ProductEntity productEntity = productMapper.mapToEntity(productDto);
        productRepository.save(productEntity);
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

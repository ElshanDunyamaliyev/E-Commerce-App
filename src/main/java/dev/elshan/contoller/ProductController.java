package dev.elshan.contoller;


import dev.elshan.dto.ProductDto;
import dev.elshan.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<ProductDto> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/products/keyword/{keyword}")
    public List<ProductDto> getProductsByKeyword(@PathVariable String keyword) {
        return productService.getProductsByKeyword(keyword);
    }

    @PostMapping("/categories/{categoryId}/products")
    public void addProduct(@PathVariable Long categoryId,@RequestBody ProductDto productDto) {
        productService.createProduct(categoryId,productDto);
    }

    @PatchMapping("/{productId}")
    public void updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}

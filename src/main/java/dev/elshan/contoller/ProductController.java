package dev.elshan.contoller;


import dev.elshan.dto.ProductDto;
import dev.elshan.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/categories/{categoryId}/product")
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

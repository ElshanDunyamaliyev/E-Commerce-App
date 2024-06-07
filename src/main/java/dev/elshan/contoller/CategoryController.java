package dev.elshan.contoller;

import dev.elshan.dto.CategoryDto;
import dev.elshan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
    }

    @PatchMapping("/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteProductById(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}

package dev.elshan.contoller;

import dev.elshan.dto.CategoryDto;
import dev.elshan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(@RequestParam(required = false,defaultValue = "0") Integer pageNumber,@RequestParam(required = false,defaultValue = "20") Integer pageSize,
                                              @RequestParam(required = false,defaultValue = "asc") String sortOrder,@RequestParam(required = false,defaultValue = "categoryId") String sortBy) {
        return categoryService.getAllCategories(pageNumber,pageSize,sortOrder,sortBy);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
    }

    @PatchMapping("/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}

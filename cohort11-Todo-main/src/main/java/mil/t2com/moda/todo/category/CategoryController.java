package mil.t2com.moda.todo.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveNewCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return null;
    }

    // ADD with Tests for: GetById, Put, Delete

    // Example
    //@GetMapping("/{categoryId}")

}

package organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import organic.model.Category;
import organic.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")

public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getCategories() {
        return service.getAllCategories();
    }

    @PostMapping
    public Category addCategory(
            @RequestBody Category category) {

        return service.addCategory(category);
    }

}
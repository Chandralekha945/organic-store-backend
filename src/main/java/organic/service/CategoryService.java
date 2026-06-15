package organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organic.model.Category;
import organic.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category addCategory(Category category) {
        return repository.save(category);
    }
}
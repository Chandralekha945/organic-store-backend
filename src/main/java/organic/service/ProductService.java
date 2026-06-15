package organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organic.model.Product;
import organic.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = repository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        if (updated.getImage() != null) {
            existing.setImage(updated.getImage());
        }
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
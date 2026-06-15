package organic.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import organic.model.Product;
import organic.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    // GET all products
    @GetMapping
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    // GET single product by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product p = service.getProductById(id);
        if (p == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Product not found");
            return ResponseEntity.status(404).body(err);
        }
        return ResponseEntity.ok(p);
    }

    // POST add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // POST upload product image
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) {
        try {
            String uploadDir = "uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.write(filePath, file.getBytes());

            Map<String, String> res = new HashMap<>();
            res.put("imageUrl", "http://localhost:8080/uploads/" + filename);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Upload failed: " + e.getMessage());
            return ResponseEntity.status(500).body(err);
        }
    }

    // PUT update product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updated) {
        Product p = service.updateProduct(id, updated);
        if (p == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Product not found");
            return ResponseEntity.status(404).body(err);
        }
        return ResponseEntity.ok(p);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        Map<String, String> res = new HashMap<>();
        res.put("message", "Product deleted successfully");
        return ResponseEntity.ok(res);
    }
}
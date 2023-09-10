package ntu.edu.sg.project3.service;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.entity.Product;
import ntu.edu.sg.project3.repository.CategoryRepository;
import ntu.edu.sg.project3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}

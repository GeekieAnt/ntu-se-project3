package ntu.edu.sg.project3.service;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.entity.Product;
import ntu.edu.sg.project3.exception.CategoryNotFoundException;
import ntu.edu.sg.project3.exception.ProductNotFoundException;
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
    public Product create(Product product, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product save(Product product) {
        Category category = product.getCategory();
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {

        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setName(product.getName());
        productToUpdate.setPhoto(product.getPhoto());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setQuantity(productToUpdate.getQuantity());
        productToUpdate.setSold(product.getSold());
        productToUpdate.setCategory(product.getCategory());

        return productRepository.save(productToUpdate);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return productRepository.findByCategory(category);
    }
}

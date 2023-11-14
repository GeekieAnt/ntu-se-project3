package ntu.edu.sg.project3.service;

import ntu.edu.sg.project3.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getOne(Long id);

    Product create(Product product, Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void delete(Long id);

    List<Product> getProductsByCategory(Long categoryId);
}

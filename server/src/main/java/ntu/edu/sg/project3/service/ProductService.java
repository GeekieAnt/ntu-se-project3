package ntu.edu.sg.project3.service;

import ntu.edu.sg.project3.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product create(Product product, Long categoryId);

    Product getOne(Long id);

    void delete(Long id);
}

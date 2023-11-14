package ntu.edu.sg.project3.repository;

import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategory(Category category);
}

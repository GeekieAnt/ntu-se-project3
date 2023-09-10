package ntu.edu.sg.project3.repository;

import ntu.edu.sg.project3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}


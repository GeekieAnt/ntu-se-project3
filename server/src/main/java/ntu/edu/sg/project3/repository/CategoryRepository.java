package ntu.edu.sg.project3.repository;

import ntu.edu.sg.project3.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

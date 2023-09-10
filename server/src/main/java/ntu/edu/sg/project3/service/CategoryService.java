package ntu.edu.sg.project3.service;

import java.util.List;

import ntu.edu.sg.project3.entity.Category;

public interface CategoryService {
  List<Category> getAll();

  Category create(Category category);

  Category getOne(Long id);

  void delete(Long id);
}

package ntu.edu.sg.project3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.exception.CategoryNotFoundException;
import ntu.edu.sg.project3.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Category getOne(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }

  @Override
  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Category update(Long id, Category category) {
    Category categoryToUpdate = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    categoryToUpdate.setName(category.getName());
    return categoryRepository.save(categoryToUpdate);
  }

}

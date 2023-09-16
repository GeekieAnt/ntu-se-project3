package ntu.edu.sg.project3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

  private CategoryService categoryService;

  @PostMapping("")
  public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
    return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategory() {
    return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Category> getCategory(@PathVariable Long id) {
    Category foundCategory = categoryService.getOne(id);
    return new ResponseEntity<>(foundCategory, HttpStatus.OK);

  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
    categoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    return new ResponseEntity<>(categoryService.update(id, category), HttpStatus.OK);
  }

}

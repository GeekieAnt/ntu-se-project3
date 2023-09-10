package ntu.edu.sg.project3.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.service.CategoryService;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

  private CategoryService categoryService;

  @PostMapping("")
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategory() {
    return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
  }

}

package ntu.edu.sg.project3.controller;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.entity.Product;
import ntu.edu.sg.project3.service.CategoryService;
import ntu.edu.sg.project3.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        return new ResponseEntity<>(productService.create(product, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getOneProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getOne(productId), HttpStatus.OK);
    }
}

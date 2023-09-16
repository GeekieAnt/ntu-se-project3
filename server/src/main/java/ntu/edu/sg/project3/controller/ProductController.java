package ntu.edu.sg.project3.controller;

import lombok.AllArgsConstructor;
import ntu.edu.sg.project3.entity.Product;
import ntu.edu.sg.project3.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("category/{id}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable Long id) {
        return new ResponseEntity<>(productService.create(product, id), HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getOne(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
    }

}

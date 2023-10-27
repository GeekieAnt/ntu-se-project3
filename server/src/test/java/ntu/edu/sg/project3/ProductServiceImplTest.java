package ntu.edu.sg.project3;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import ntu.edu.sg.project3.entity.Product;
import ntu.edu.sg.project3.repository.CategoryRepository;
import ntu.edu.sg.project3.repository.ProductRepository;
import ntu.edu.sg.project3.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  ProductServiceImpl productService;

  @Test
  public void CreateTest() {

    // set up
    Product product = Product.builder().name("Java in Depth").description("Learn Java in Dept").price(99.0).quantity(99)
        .build();
    // Mock save method of repository
    when(productRepository.save(product)).thenReturn(product);
    // Execute
    Product savedProduct = productService.save(product);
    // Assert
    assertEquals(product, savedProduct, "The saved product shouild be the same as the input product");

  }

  @Test
  public void getOneTest() {
    // set up
    Product product = Product.builder().name("Java in Depth").description("Learn Java in Dept").price(99.0).quantity(99)
        .build();

    Long productId = 1L;

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    // Execute
    Product retrievedProduct = productService.getOne(productId);

    // Assert
    assertEquals(product, null, "The retrieved product should be the same as the input product");

  }
}

package ntu.edu.sg.project3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ntu.edu.sg.project3.entity.Category;
import ntu.edu.sg.project3.exception.CategoryNotFoundException;
import ntu.edu.sg.project3.repository.CategoryRepository;
import ntu.edu.sg.project3.service.CategoryServiceImpl;

@SpringBootTest
public class CategoryServiceImplTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  CategoryServiceImpl categoryService;

  @Test
  public void createTest() {

    // 1. SETUP / ARRANGE
    Category category = Category.builder().name("Bash").build();

    // Mock the save method of customer repository
    when(categoryRepository.save(category)).thenReturn(category);

    // 2. EXECUTE
    // Call the method that we want to test
    Category savedCategory = categoryService.create(category);

    // 3. ASSERT
    assertEquals(category, savedCategory, "The saved category should be the same as the input category");

    // Also verify that the save method was called
    verify(categoryRepository, times(1)).save(category);
  }

  @Test
  public void getOneTest() {

    // 1. SETUP
    Category category = Category.builder().name("Bash").build();

    Long categoryId = 10L;

    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

    // 2. EXECUTE
    Category retrievedCategory = categoryService.getOne(categoryId);

    // 3. ASSERT
    assertEquals(category, retrievedCategory, "The retrieved category should be the same as the input category");
  }

  @Test
  public void getNotFoundTest() {
    Long categoryId = 99L;
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

    // Assert
    assertThrows(CategoryNotFoundException.class, () -> categoryService.getOne(categoryId));

  }

}

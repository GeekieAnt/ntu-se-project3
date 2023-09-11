package ntu.edu.sg.project3;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ntu.edu.sg.project3.repository.CategoryRepository;
import ntu.edu.sg.project3.service.CategoryServiceImpl;

@SpringBootTest
public class CategoryServiceImplTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  CategoryServiceImpl categoryService;

}

package ntu.edu.sg.project3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAllCategoryTest() throws Exception {
    // Step 1: Build a request
    RequestBuilder request = MockMvcRequestBuilders.get("/category");

    // Step 2: Perform the request, get the response and assert
    mockMvc.perform(request)
        // Assert that the status code is 200 OK
        .andExpect(status().isOk())
        // Assert the content is JSON
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}

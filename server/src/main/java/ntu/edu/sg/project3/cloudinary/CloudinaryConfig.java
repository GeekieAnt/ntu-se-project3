package ntu.edu.sg.project3.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
  private final String CLOUD_NAME = "dwjt4wb3n";
  private final String API_KEY = "459396152743996";
  private final String API_SECRET = "MHrGwBOj5NlJeMOVWbfWL0_QoFk";

  @Bean
  public Cloudinary cloudinary() {
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", CLOUD_NAME);
    config.put("api_key", API_KEY);
    config.put("api_secret", API_SECRET);
    return new Cloudinary(config);
  }
}

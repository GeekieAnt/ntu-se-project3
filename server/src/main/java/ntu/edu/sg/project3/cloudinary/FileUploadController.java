package ntu.edu.sg.project3.cloudinary;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@RestController
@AllArgsConstructor
public class FileUploadController {
  private final FileUpload fileUpload;

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("image") MultipartFile multipartFile,
      Model model) throws IOException {
    String imageURL = fileUpload.uploadFile(multipartFile);
    model.addAttribute("imageURL", imageURL);
    return imageURL;
  }
}
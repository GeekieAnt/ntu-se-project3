package ntu.edu.sg.project3.cloudinary;

import com.cloudinary.Cloudinary;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileUploadImpl implements FileUpload {
  private final Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile multipartFile) throws IOException {
    return cloudinary.uploader()
        .upload(multipartFile.getBytes(),
            Map.of("public_id", UUID.randomUUID().toString()))
        .get("url")
        .toString();
  }
}
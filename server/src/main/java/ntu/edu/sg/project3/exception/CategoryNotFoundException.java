package ntu.edu.sg.project3.exception;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(Long id) {
    super("The category id '" + id + "' does not exist in our records");
  }
}

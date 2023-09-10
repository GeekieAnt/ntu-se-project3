package ntu.edu.sg.project3.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("The product id '" + id + "' does not exist in our records");
  }
}

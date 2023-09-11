package ntu.edu.sg.project3.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
  private String name;

  @Column(name = "description")
  private String description;

  @Min(1)
  @Column(name = "price")
  private Double price;

  @Min(1)
  @Column(name = "quantity")
  private int quantity;

  @Column(name = "sold")
  private int sold;

  @Column(name = "photo")
  private String photo;

  @ManyToOne
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;

  @Builder
  public Product(String name, String description, double price, int quantity) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }

}

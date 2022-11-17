package homework;

import java.time.LocalDateTime;

public class Product {

  private int idNumber;
  private final String type;
  private float price;
  private boolean isDiscount;
  private LocalDateTime dateAdding;

  public Product(int idNumber, String type, float price, boolean isDiscount,
      LocalDateTime dateAdding) {
    this.idNumber = idNumber;
    this.type = type;
    this.price = price;
    this.isDiscount = isDiscount;
    this.dateAdding = dateAdding;
  }

  public String getType() {
    return type;
  }

  public float getPrice() {
    return price;
  }

  public boolean isDiscount() {
    return isDiscount;
  }

  /**this method applies discount to the price of Product*/
  public void discount(int discount) {
    this.price *= (float) (100 - discount) / 100;
  }

  public LocalDateTime getDateAdding() {
    return dateAdding;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public String toString() {
    return "\nProduct{" +
        "idNumber=" + idNumber +
        ", type='" + type + '\'' +
        ", price=" + price +
        ", isDiscount=" + isDiscount +
        ", dateAdding=" + dateAdding +
        '}';
  }
}

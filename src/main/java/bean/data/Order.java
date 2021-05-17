package bean.data;

import lombok.Getter;
import lombok.Setter;

/**
 * ALDI Product data Model
 * Use this model for test user data e.g Product Name , Product price
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
public class Order {

  private String orderNumber;
  private String outstandingPrice; // This contains price with delivery fee
  private String orderPrice; // this contains only the total order price without delivery fee

  public Order() {
  }

  @Override
  public String toString() {
    String userData = "\n ******************************************\n BaseALDIActor{"
        + ",\n productName'" + orderNumber + '\''
        + ",\n product Add Message'" + outstandingPrice + '\''
        + '}' + "\n";

    return userData;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getOutstandingPrice() {
    return outstandingPrice;
  }

  public void setOutstandingPrice(String outstandingPrice) {
    this.outstandingPrice = outstandingPrice;
  }

  public String getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(String orderPrice) {
    this.orderPrice = orderPrice;
  }
}

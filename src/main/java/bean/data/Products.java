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
public class Products {

  private String productName;
  private String productAddSuccessMsg;
  private String productPrice;
  private String productRemoveMessage;
  private String cartPrice;

  public Products() {
  }

  @Override
  public String toString() {
    String userData = "\n ******************************************\n BaseALDIActor{"
        + ",\n productName'" + productName + '\''
        + ",\n product Add Message'" + productAddSuccessMsg + '\''
        + ",\n product price'" + productPrice + '\''
        + '}' + "\n";

    return userData;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductAddSuccessMsg() {
    return productAddSuccessMsg;
  }

  public void setProductAddSuccessMsg(String productAddSuccessMsg) {
    this.productAddSuccessMsg = productAddSuccessMsg;
  }

  public String getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(String productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductRemoveMessage() {
    return productRemoveMessage;
  }

  public void setProductRemoveMessage(String productRemoveMessage) {
    this.productRemoveMessage = productRemoveMessage;
  }

  public String getCartPrice() {
    return cartPrice;
  }

  public void setCartPrice(String cartPrice) {
    this.cartPrice = cartPrice;
  }
}

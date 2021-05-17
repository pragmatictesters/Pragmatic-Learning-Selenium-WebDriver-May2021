package bean.data;

import lombok.Getter;
import lombok.Setter;

/**
 * ALDI User data Model
 * Use this model for test user data e.g username, pass
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
public class BaseAldiUser {
  protected String email;
  protected String password;

  public BaseAldiUser() {

  }

  public BaseAldiUser(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    String userData = "\n ******************************************\n BaseALDIActor{"
        + ",\n clientUserUUID'" + email + '\''
        + ",\n clientUserID'" + password + '\''
        + '}' + "\n";

    return userData;
  }
}

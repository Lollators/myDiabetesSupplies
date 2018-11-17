package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserPanelController {

  @FXML
  public TextField username;

  @FXML
  public PasswordField password;

  @FXML
  public TextField name;

  @FXML
  public TextField usernameDel;

  @FXML
  public CheckBox deletionCheck;

  /**
   * Function createAccount gets name, password, and username from user input.
   * Runs query to add new user to user table.
   * TODO: check if user already exists
   *
   * @param actionEvent - mouse click
   */
  public void createAccount(ActionEvent actionEvent) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);

    //create query
    String query = "INSERT INTO user(name, username, password) VALUES ('" + name.getText()
        + "', '" + username.getText() + "', '" + password.getText() + "');";
    try {
      //run query
      if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
        alert.setContentText("New account created successfully!");
        alert.showAndWait();

        //reset TextFields and PasswordField so that they are empty for next use
        name.setText("");
        username.setText("");
        password.setText("");

        //return to home screen
        Main.setPane(Screens.HOME.getValue());
      } else {
        System.err.println("FAILED TO RUN QUERY");
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * Function deleteAccount gets username of user to delete from user input.
   * Runs query only if deletionCheck is selected (as it is an irreversible operation).
   * TODO: check if user exists before trying to delete it
   *
   * @param actionEvent - mouse click
   */
  public void deleteAccount(ActionEvent actionEvent) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);

    if (deletionCheck.isSelected()) {
      //create query
      String query = "DELETE FROM user WHERE username='" + usernameDel.getText() + "';";
      try {
        //run query
        if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
          alert.setContentText("Account deleted successfully!");
          alert.showAndWait();

          //reset textField and deselect deletionCheck for next use
          usernameDel.setText("");
          deletionCheck.setSelected(false);

          //return to home screen
          Main.setPane(Screens.HOME.getValue());
        } else {
          System.err.println("FAILED TO RUN QUERY");
        }
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }

  }

  //return to main menu
  public void back(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }
}

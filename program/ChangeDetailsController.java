package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ChangeDetailsController {

  @FXML
  public TextField currentValue;

  @FXML
  public TextField newValue;

  @FXML
  public TextField newValueCheck;

  @FXML
  public ToggleButton toggleUsername;

  @FXML
  public ToggleButton toggleName;

  @FXML
  public ToggleButton togglePassword;

  private ToggleGroup group = new ToggleGroup();

  @FXML
  public Label wrongValue;

  /**
   * Function initialize set toggle group for toggleButtons so when one is clicked
   * the others are not.
   */
  public void initialize() {
    toggleName.setToggleGroup(group);
    togglePassword.setToggleGroup(group);
    toggleUsername.setToggleGroup(group);

  }

  /**
   * Function changeDetails checks which toggleButton is selected.
   * Based on this, asks user to insert the old value, a new value, and retype the new value
   * (for control purposes). Then runs query to update Database with new value.
   *
   * @param actionEvent - mouse click
   */
  public void changeDetails(ActionEvent actionEvent) {
    wrongValue.setText("");
    wrongValue.setVisible(false);
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);

    //if name is selected
    if (toggleName.isSelected()) {
      if (checkNewValue() == 1) {
        //create query
        String query = "SELECT * FROM user WHERE name='" + currentValue.getText() + "';";
        try {
          //run query and save result set
          ResultSet rs = DBconn.queryDb(query, DBconn.startConn());
          if (rs != null) {
            //if the result set has at least one value then it means the value inserted was valid
            if (rs.next()) {
              //then update with new value typed by the user
              query = "UPDATE user SET name='" + newValue.getText() + "' WHERE username='"
                  + Main.loggedUser + "';";
              if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
                alert.setContentText("Successfully changed your name!");
                alert.showAndWait();
                Main.setPane(Screens.HOME.getValue());
              } else {
                System.err.println("Failed to run Update");
              }
            } else {
              wrongValue.setText("Could not find matching current value");
              wrongValue.setVisible(true);
            }
          } else {
            System.err.println("FAILED TO RUN QUERY");
          }
        } catch (SQLException e) {
          System.out.println(e.toString());
        }
      }

      //if password is selected
    } else if (togglePassword.isSelected()) {
      if (checkNewValue() == 1) {
        //create query
        String query = "SELECT * FROM user WHERE password='" + currentValue.getText() + "';";
        try {
          //run query and save result set
          ResultSet rs = DBconn.queryDb(query, DBconn.startConn());
          if (rs != null) {
            //if the result set has at least one value then it means the value inserted was valid
            if (rs.next()) {
              //then update with new value typed by the user
              query = "UPDATE user SET password='" + newValue.getText() + "' WHERE username='"
                  + Main.loggedUser + "';";
              if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
                alert.setContentText("Successfully changed your password!");
                alert.showAndWait();
                Main.setPane(Screens.HOME.getValue());
              } else {
                System.err.println("Failed to run Update");
              }
            } else {
              wrongValue.setText("Could not find matching current value");
              wrongValue.setVisible(true);
            }
          } else {
            System.err.println("FAILED TO RUN QUERY");
          }
        } catch (SQLException e) {
          System.out.println(e.toString());
        }
      }

      //if username is selected
    } else if (toggleUsername.isSelected()) {
      if (checkNewValue() == 1) {
        //create query
        String query = "SELECT * FROM user WHERE username='" + currentValue.getText() + "';";
        try {
          //run query and save result set
          ResultSet rs = DBconn.queryDb(query, DBconn.startConn());
          if (rs != null) {
            //if the result set has at least one value then it means the value inserted was valid
            if (rs.next()) {
              //then update with new value typed by the user
              query = "UPDATE user SET username='" + newValue.getText() + "' WHERE username='"
                  + Main.loggedUser + "';";
              if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
                alert.setContentText("Successfully changed your username!");
                alert.showAndWait();
                Main.setPane(Screens.HOME.getValue());
              } else {
                System.err.println("Failed to run Update");
              }
            } else {
              wrongValue.setText("Could not find matching current value");
              wrongValue.setVisible(true);
            }
          } else {
            System.err.println("FAILED TO RUN QUERY");
          }
        } catch (SQLException e) {
          System.out.println(e.toString());
        }
      }

    } else {
      wrongValue.setText("Select an option!");
      wrongValue.setVisible(true);
    }
  }

  /**
   * Function checkNewValue, checks if the new value that the user typed, and the "control"
   * retyped value are equal. If so, returns 1. If not, returns -1 and shows wrong value text.
   *
   * @return int - status of operation (1 = ok, -1 = error)
   */
  public int checkNewValue() {
    int status = 1;

    if (!newValue.getText().equals(newValueCheck.getText())) {
      wrongValue.setText("New values don't match! Type carefully!");
      wrongValue.setVisible(true);
      status = -1;
    }

    return status;
  }

  //return to main menu
  public void back(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }
}

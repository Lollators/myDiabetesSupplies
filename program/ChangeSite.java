package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ChangeSite {

  @FXML
  private TextField tankSerial;

  @FXML
  private TextField siteSerial;

  @FXML
  private TextField insulinSerial;

  //return to main menu
  public void goBack(ActionEvent actionEvent) {
    Main.setPane(Screens.HOME.getValue());
  }

  /**
   * When you change your site, you use one tank, one site, and one insulin vial.
   * Therefore the changeSite function takes the serial number of each of those 3 product,
   * and runs a query that removes them from the database.
   * The user is required to enter just the numeric portion of the serial number, which is
   * 6 digits.
   *
   * @param actionEvent - mouse click
   */
  public void changeSite(ActionEvent actionEvent) {
    String query = "";
    String tankSerialNumber = "";
    String siteSerialNumber = "";
    String insulinSerialNumber = "";
    Alert alert2 = new Alert(AlertType.ERROR);
    alert2.setTitle("Error!");
    alert2.setHeaderText(null);
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText(null);

    //check if serial numbers entered by the user are the right length
    if (tankSerial.getLength() != 6 || siteSerial.getLength() != 6
        || insulinSerial.getLength() != 6) {
      alert2.setContentText("One or more serial number length don't match expected value");
      alert2.showAndWait();
    } else {
      //create full serial number with code for each product
      tankSerialNumber = "TN" + tankSerial.getText();
      siteSerialNumber = "ST" + siteSerial.getText();
      insulinSerialNumber = "IN" + insulinSerial.getText();

      //create query
      query = "DELETE FROM product WHERE serialNumber='" + tankSerialNumber
          + "' OR serialNumber='" + siteSerialNumber + "' OR serialNumber='"
          + insulinSerialNumber + "';";

      try {
        //run query
        if (DBconn.updateDb(query, DBconn.startConn()) != -1) {
          alert.setContentText("Site changed successfully, products count updated!");
          alert.showAndWait();
          Main.setPane(Screens.HOME.getValue());
        } else {
          alert2.setContentText("Database/Query issue");
          alert2.showAndWait();
        }
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }

  }
}

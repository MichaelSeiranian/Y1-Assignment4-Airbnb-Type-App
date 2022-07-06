import javafx.scene.control.Alert;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class deals with all alert creation.
 * If there is a need for an alert to occur, the alert can be created in this class.
 *
 * @author israfeel_ashraf
 * @version 22/03/22
 */
public class AirbnbAlertCreation {

    public AirbnbAlertCreation() {}

    /**
     * Invalid price alert method.
     * This method is called when there is an invalid price selected by the user,
     * when the max price is less than or equal to the min price.
     */
    private void invalidPriceAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About AirbnbApplication");
        alert.setHeaderText(null);  // Alerts have an optional header.
        alert.setContentText("Airbnb\n" + "Version: " + 6.0 + "\n\nMinimum price range must be greater than maximum price range.");
        alert.showAndWait();
    }

    /**
     * Method made available to other classes to get the price alert.
     */
    public void getInvalidPriceAlert() {
        invalidPriceAlert();
    }
}

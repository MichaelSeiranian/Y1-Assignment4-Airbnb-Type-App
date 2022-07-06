import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 * 
 * This class deals with opening up the description window,
 * when the user clicks on a property in the borough window.
 *
 * @author israfeel_ashraf  K21008936
 * @author shakeeb_jumaan   K21087021
 * @version 22/03/22
 */
public class DescriptionWindow {

    private Label descriptionLabel = new Label("HOUSE DESCRIPTION: ");

    public DescriptionWindow() {}

    /**
     * Add a title pane to the window.
     * @return The title pane which will be at the top of the root.
     */
    private BorderPane addTitle() {
        BorderPane titlePane = new BorderPane();

        titlePane.setId("titlePane");
        titlePane.setLeft(descriptionLabel);

        return titlePane;
    }

    /**
     * This method gets the new window to show the description.
     * @param titleText the name of the borough
     * @param houseDescriptionText the description of the selected property.
     * @return The whole window containing everything.
     */
    public Stage openDescriptionWindow(String titleText, String houseDescriptionText) {

        Stage descriptionWindow = new Stage();
        descriptionWindow.setTitle("Description: " + titleText + " Airbnb");

        //Create the root.
        BorderPane rootPane = new BorderPane();

        //Add the title pane to the top of the root.
        BorderPane titlePane = addTitle();
        rootPane.setTop(titlePane);

        //Add the description to the centre of the root.
        BorderPane contentPane = addContentPane(houseDescriptionText);
        rootPane.setCenter(contentPane);

        //Create a new scene, and add the stylesheet.
        Scene descriptionScene = new Scene(rootPane, 400, 150);
        descriptionScene.getStylesheets().add("Panel_2_Description_Window.css");

        //Set the scene of the stage.
        descriptionWindow.setScene(descriptionScene);

        return descriptionWindow;
    }

    /**
     * This method creates the content (the description of the property).
     * @param text The description of the property.
     * @return the central content pane.
     */
    private BorderPane addContentPane(String text) {

        BorderPane contentPane = new BorderPane();

        Label houseDescriptionLabel = new Label(text);
        houseDescriptionLabel.setId("descriptionLabel");

        contentPane.setCenter(houseDescriptionLabel);

        return contentPane;
    }

}

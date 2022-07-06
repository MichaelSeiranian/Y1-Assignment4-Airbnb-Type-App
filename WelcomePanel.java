import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 * 
 * A class to design the welcome panel of the application.
 *
 * @author israfeel_ashraf
 * @version 22/03/22
 */
public class WelcomePanel {

    private final String minPriceTxt = "From: ", maxPriceTxt = "To: ";
    private final String PREV = "<", NEXT = ">";

    //Two buttons to navigate the panels.
    private Button previousButton, nextButton;

    //Boxes to select the prices of the properties.
    private ComboBox<Integer> minPrice, maxPrice;

    private Label minPriceLabel, maxPriceLabel, welcomeLabel, finalPriceRange;

    //The two prices which will contain the user's selected price.
    private int selectedMinPrice = -1, selectedMaxPrice = -1;

    //A scene to return.
    private Scene scene;

    /**
     * Constructor to set up the components of the GUI.
     */
    public WelcomePanel() {
        minPriceLabel = new Label(minPriceTxt);
        maxPriceLabel = new Label(maxPriceTxt);
        welcomeLabel = new Label("");
        finalPriceRange = new Label("");

        previousButton = new Button(PREV);
        nextButton = new Button(NEXT);

        //Set ids to style the buttons.
        previousButton.setId("navigationButtons");
        nextButton.setId("navigationButtons");

        minPrice = new ComboBox<>();
        maxPrice = new ComboBox<>();

        //Add prices to the boxes, so the user can select a price.
        minPrice.getItems().addAll(0, 25, 50, 75, 100, 250, 500, 750 ,1000, 2000, 3000, 4000, 5000);
        maxPrice.getItems().addAll(50, 75, 100, 250, 500, 750, 1000, 2000, 3000, 4000, 5000, 6000, 7000);

        //Set the welcome label for the user to see.
        setWelcomeLabel();
    }

    /**
     * Create the button pane at the bottom of the panel.
     * @return the borderpane containing the components.
     */
    public BorderPane addBorderPane() {

        BorderPane borderPane = new BorderPane();

        // Set a special CSS id for styling.
        borderPane.setId("borderPane");

        previousButton.setPrefSize(75,20);
        borderPane.setLeft(previousButton);

        nextButton.setPrefSize(75, 20);
        borderPane.setRight(nextButton);

        return borderPane;
    }

    /**
     * Create the scene (the first panel).
     * This will be called and dealt with by the airbnb application controller.
     * @return The first welcome panel scene.
     */
    public Scene welcomePanel() {

        BorderPane root = new BorderPane();
        root.setId("root");

        BorderPane buttonsPanel = addBorderPane();
        root.setBottom(buttonsPanel);

        HBox hbox = addHBox();
        root.setTop(hbox);

        //Set up the centre of the application as a nested borderpane.
        BorderPane contentPane = new BorderPane();
        root.setCenter(contentPane);

        //Make the buttons unavailable at the start.
        setUnavailable(true);

        //Create the scence
        scene = new Scene(root, 1200, 750);

        //Added the stylesheet to the class, so styling can be done separately in that class.
        scene.getStylesheets().add("Panel_1_StyleSheet.css");

        contentPane.setCenter(getWelcomeLabel());
        getFinalPriceRange().setId("priceLabel");

        contentPane.setBottom(getFinalPriceRange());
        BorderPane.setAlignment(getFinalPriceRange(), Pos.BOTTOM_CENTER);

        return scene;

    }

    /**
     * Make the top of the screen with the choice boxes and the labels.
     * @return The horizontal box to add to the top of the scene.
     */
    public HBox addHBox() {
        HBox hbox = new HBox();
        //Set special styling ids for the CSS file.
        hbox.setId("hbox");

        minPriceLabel.setPrefHeight(25);
        maxPriceLabel.setPrefHeight(25);

        minPrice.setPromptText("--");
        maxPrice.setPromptText("--");

        hbox.getChildren().addAll(minPriceLabel, minPrice, maxPriceLabel, maxPrice);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        return hbox;
    }

    /**
     * Set the welcome label of the first panel.
     */
    private void setWelcomeLabel() {
        welcomeLabel.setId("welcome_label");
        welcomeLabel.setText("Welcome to the Airbnb London application!" +
                "\nThis application lets you find and view airbnb properties in London.\n" +
                "\n1: Select your price range." +
                "\n2: Navigate to the next panel and select the borough you'd like to browse." +
                "\n3: Browse through the available properties.");
    }

    /**
     * A method to show the user their final selected price.
     */
    public void setFinalPriceRange() {
        //Make sure that the user has selected a price range.
        if ((getSelectedMinPrice() > -1) && (getSelectedMaxPrice() > -1)) {
            finalPriceRange.setText("SELECTED PRICE:   £" + getSelectedMinPrice() + " to " + "£" + getSelectedMaxPrice());
        } else {
            finalPriceRange.setText("");
        }
    }

    /**
     * A method to make the buttons available or not.
     * @param x The boolean value of whether the buttons should be disabled or not.
     */
    public void setUnavailable(boolean x) {
        previousButton.setDisable(x);
        nextButton.setDisable(x);
    }

    /*
      Below are all the accessor and mutator methods for the controller to use.
     */

    public Label getWelcomeLabel() {return welcomeLabel; }

    public void setSelectedMinPrice(int a) {
        this.selectedMinPrice = a;
    }

    public void setSelectedMaxPrice(int a) {
        this.selectedMaxPrice = a;
    }

    public int getSelectedMinPrice() {
        return selectedMinPrice;
    }

    public int getSelectedMaxPrice() {
        return selectedMaxPrice;
    }

    public ComboBox<Integer> getMinPrice() {
        return minPrice;
    }

    public ComboBox<Integer> getMaxPrice() {
        return maxPrice;
    }

    public Label getFinalPriceRange() {
        return finalPriceRange;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }
}

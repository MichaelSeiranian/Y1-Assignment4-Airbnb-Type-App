import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * A class to deal with the map scene (the second panel)
 * This class creates the map scene, and allows the user to see the boroughs of
 * London in a geographical manner, which they can then select in order to see the
 * available properties in that borough, accoring to the price range.
 *
 * @author israfeel_ashraf K21008936
 * @author shakeeb_jumaan K21087021
 *
 * @version 22/03/22
 *
 */
public class MapScene_2 {

    //Fields of all the buttons that represent the boroughs of London.
    private Button b1 = new Button("HRRW"), b2= new Button("BREN"),b3 = new Button("CAMD"), b4 = new Button("ISLI"), b5 = new Button("HACK"), b6 = new Button("REDB"), b7 = new Button("HAVE");
    private Button b8 = new Button("HILL"), b9=  new Button("EALI"),b10 = new Button("KENS"), b11 = new Button("WSTM"), b12 = new Button("TOWH"), b13 = new Button("NEWH"), b14 = new Button("BARK");
    private Button b15 = new Button("HOUN"), b16= new Button("HAMM"),b17 = new Button("WAND"), b18 =new Button("CITY"), b19 =new Button("GWCH"), b20 = new Button("BEXL");
    private Button b21 = new Button("RICH"), b22= new Button("MERT"),b23 = new Button("LAMB"), b24 = new Button("STHW"), b25 =new Button("LEWS");
    private Button b26 = new Button("KING"), b27= new Button("SUTT"), b28 = new Button("CROY"), b29 = new Button("BROM");
    private Button b30 = new Button("BARN"), b31= new Button("HRGY"),b32 = new Button("WALT");
    private Button b33 = new Button("ENFI");

    private Button[] buttonList = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,
            b11, b12, b13, b14, b15, b16, b17, b18, b19, b20,
            b21, b22, b23, b24, b25, b26, b27, b28, b29, b30,
            b31, b32, b33};

    private Label boroughLabel;

    //The scene of the second panel to return.
    private Scene scene;

    private final String PREV = "<", NEXT = ">", boroughStr = "SELECT A BOROUGH:";

    //Two buttons to navigate the panels.
    private Button previousButton, nextButton;

    //The filtered property listings, based on the input price that the user selects.
    private AirbnbFilteredListings theListings = new AirbnbFilteredListings();

    private int minPrice, maxPrice;

    //A colours object, to deal with the representation of the number of properties in that borough.
    private Colours colours = new Colours();

    //A borough window object, that opens when a borough is selected.
    private BoroughWindow boroughWindow;

    /**
     * A constructor to deal with the setting up of the GUI components.
     */
    public MapScene_2() {
        previousButton = new Button(PREV);
        nextButton = new Button(NEXT);
        boroughLabel = new Label(boroughStr);

        //Set the button ids, so they can be styled appropriately.
        setIDs();

        previousButton.setId("navigationButtons");
        nextButton.setId("navigationButtons");

    }

    /**
     * A method to iterate through the borough buttons and assign a styling id to them.
     */
    private void setIDs() {
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setId("locationButtons");
        }
    }

    /**
     * Add the bottom button bar panel, so navigation can occur.
     * @return
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
     * A method to add a title pane to the GUI.
     * @return The borderpane to add to the root of the scene.
     */
    public BorderPane addTitle() {
        BorderPane titlePane = new BorderPane();
        titlePane.setId("titlePane");

        titlePane.setLeft(boroughLabel);
        return titlePane;
    }

    /**
     * A method to return the second panel, which the airbnb application controller
     * will deal with.
     * @return The second panel scene.
     */
    public Scene mapScene() {

        //A method to set up the borough buttons.
        Pane pane = setUpCentre();

        //An event handling method to set the buttons up so they respond when clicked.
        setEventHandling();

        BorderPane mapBorderPane = new BorderPane();
        mapBorderPane.setCenter(pane);

        BorderPane buttonPane = addBorderPane();
        mapBorderPane.setBottom(buttonPane);

        BorderPane titlePane = addTitle();
        mapBorderPane.setTop(titlePane);

        //Create the scene and add a style sheet to it.
        scene = new Scene(mapBorderPane, 1200, 750);
        scene.getStylesheets().add("Panel_2_StyleSheet.css");


        //Set ids so styling can occur.
        mapBorderPane.setId("root");
        buttonPane.setId("borderPane");

        return scene;
    }

    /**
     * A method to add a hbox row of buttons, to create the gui map.
     *
     * @param padding The padding, to indent the rows of buttons, for aesthetics.
     * @param buttons A varargs parameter of buttons, so for each vbox, a
     *                different amount of buttons can be added.
     * @return A hbox that contains a row of buttons.
     */
    private Pane addHBox(int padding, Button... buttons) {

        HBox pane = new HBox();
        pane.setSpacing(12);
        pane.setPadding(new Insets(0,0,0, padding));

        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(buttons);

        StackPane paneToReturn = new StackPane();
        paneToReturn.getChildren().addAll(pane);
        return paneToReturn;
    }

    /**
     * A method to arrange the layout of the borough buttons.
     * Each hbox of buttons is arranged in a vbox, which is returned via this
     * method and added into the centre of the border pane.
     * @return The vbox of buttons.
     */
    private Pane setUpCentre() {
        Pane r1 = addHBox(90, b33);
        Pane r2 = addHBox(0, b30, b31, b32);
        Pane r3 = addHBox(80, b1, b2, b3, b4, b5, b6, b7);
        Pane r4 = addHBox(0, b8, b9, b10, b11, b12, b13, b14);
        Pane r5 = addHBox(0, b15, b16, b17, b18, b19, b20);
        Pane r6 = addHBox(0, b21, b22, b23, b24, b25);
        Pane r7 = addHBox(0, b26, b27, b28, b29);

        VBox pane = new VBox(r1, r2, r3, r4, r5, r6, r7);
        pane.setSpacing(1);

        //Style the vbox.
        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20;");

        return pane;
    }

    /**
     * A method to deal with the borough buttons being selected.
     * @param event The button being selected.
     */
    private void buttonPressed(ActionEvent event) {

        //Obtain which button was selected.
        Button currentButtonPushed = (Button)event.getSource();

        //Get the filtered listing of the user's price range, and which borough was selected.
        //Now, the airbnb dataset is reduced even more, to only properties within the price range in the selected borough.
        ArrayList<AirbnbListing> filteredBoroughListing = theListings.getBoroughListings(getMinPrice(), getMaxPrice(), currentButtonPushed);

        //Create a new borough window to open up, when the user selects the borough.
        boroughWindow = new BoroughWindow();

        //Pass in the filtered list to display the data to the user.
        boroughWindow.loadFilteredBoroughListings(filteredBoroughListing);

        //Create the new stage, and show it to the user.
        Stage boroughWindowToOpen = boroughWindow.openBoroughWindow(currentButtonPushed);
        boroughWindowToOpen.show();
    }

    /**
     * A method to set the event handling of all the buttons.
     * Whenever one of the borough buttons is clicked, the buttonPressed
     * method is called.
     */
    private void setEventHandling() {
        for (int i = 0; i < buttonList.length; i++) {
            buttonList[i].setOnAction(this::buttonPressed);
        }
    }

    /**
     * A method to return the key: value dictionary of boroughs.
     * This maps the button label to its corresponding value in the
     * airbnb CSV.
     * @return the hashmap of key value pairs for boroughs in london.
     */
    private HashMap<String, String> getBoroughs() {
        HashMap<String, String> boroughs = theListings.createBoroughDictionary();
        return boroughs;
    }

    /*
      Below are accessor and mutator methods for the airbnb application class to use.
     */

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public void showColours() {
        colours.showColours(getMinPrice(), getMaxPrice(), buttonList);
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }
}

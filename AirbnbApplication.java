import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * The controller of the airbnb London application.
 *
 * @author israfeel_ashraf - K21008936 
 * @author Michael Seiranian - K20127931
 * @version 22/03/22
 */
public class AirbnbApplication extends Application
{

    private ArrayList<Scene> listOfScenes = new ArrayList<>();

    private Scene panel_one, panel_two, panel_three, panel_four;

    private WelcomePanel firstPanel;

    private MapScene_2 second_Panel;

    private StatisticsPanel thirdPanel;
    
    private UndergroundPanel fourthPanel;

    private BorderPane root;

    private int panel;

    /**
     * Constructor to set up the first two panels in the application.
     */
    public AirbnbApplication(){
        firstPanel = new WelcomePanel();
        second_Panel = new MapScene_2();
        thirdPanel = new StatisticsPanel();
        fourthPanel = new UndergroundPanel();
    }

    /**
     * The start method is the main entry point for every JavaFX application.
     * It is called after the init() method has returned and after
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     * @throws IOException in the event of the FXML files of the third or fourth panel not loading properly
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        root = new BorderPane();

        panel_one = firstPanel.welcomePanel();
        listOfScenes.add(panel_one);
        panel_two = second_Panel.mapScene();
        listOfScenes.add(panel_two);
        panel_three = thirdPanel.statScene();
        listOfScenes.add(panel_three);
        panel_four = fourthPanel.undergroundScene();
        listOfScenes.add(panel_four);

        //Listen for the users selection of the maximum and minimum prices.
        firstPanel.getMinPrice().setOnAction(this::getMinimumSelectedPrice);
        firstPanel.getMaxPrice().setOnAction(this::getMaximumSelectedPrice);

        //Set the next and previous buttons on the next and previous button clicks respectively.
        firstPanel.getNextButton().setOnAction(this::nextButtonClick);
        firstPanel.getPreviousButton().setOnAction(this::previousButtonClick);

        second_Panel.getNextButton().setOnAction(this::nextButtonClick);
        second_Panel.getPreviousButton().setOnAction(this::previousButtonClick);

        thirdPanel.getNextButton().setOnAction(this::nextButtonClick);
        thirdPanel.getPreviousButton().setOnAction(this::previousButtonClick);    
        
        fourthPanel.getNextButton().setOnAction(this::nextButtonClick);
        fourthPanel.getPreviousButton().setOnAction(this::previousButtonClick);

        stage.setTitle("Air BNB London");
        stage.setScene(panel_one);

        panel = 0;
        stage.show();
    }

    /**
     * Finds the next panel and shows it
     * @param event of the button click
     */
    public void nextButtonClick(ActionEvent event) {
        //Gets the stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        //Allowing to loop through panels
        if(panel < listOfScenes.size()-1){
            panel++;
        }
        else{
            panel = 0;
        }

        //Pass the min and max price to the second panel, so it can deal with the new filtered listings.
        second_Panel.setMinPrice(firstPanel.getSelectedMinPrice());
        second_Panel.setMaxPrice(firstPanel.getSelectedMaxPrice());

        //Decide the colours of the map.
        second_Panel.showColours();

 
        stage.setScene(listOfScenes.get(panel));

        //Shows the scene.
        stage.show();
    }

    /**
     * Method to move to the previous panel.
     * @param event The button click event.
     */
    private void previousButtonClick(ActionEvent event) {
        //Gets the stage.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Sets the next scene. Allows to loop through panels.
        if(panel > 0){
            panel--;
        }
        else{
            panel = listOfScenes.size()-1;
        }
        second_Panel.setMinPrice(firstPanel.getSelectedMinPrice());
        second_Panel.setMaxPrice(firstPanel.getSelectedMaxPrice());
        stage.setScene(listOfScenes.get(panel));

        //Shows the scene.
        stage.show();
    }
    
    /**
     * Method to check whether the buttons can be enabled once the user inputs a price range.
     */
    private void shouldButtonsBeDisabled() {

        boolean bothAreAboveNegOne = ((firstPanel.getSelectedMaxPrice() >= 0) && (firstPanel.getSelectedMinPrice() >= 0));

        //If the max is greater than the minimum, enable the buttons.
        if ((firstPanel.getSelectedMaxPrice() > firstPanel.getSelectedMinPrice()) && (bothAreAboveNegOne)) {
            firstPanel.setUnavailable(false);
        }
        //If the minimum is greater than the maximum, show an alert and keep the buttons disabled.
        else if ((firstPanel.getSelectedMaxPrice() <= firstPanel.getSelectedMinPrice()) && (bothAreAboveNegOne)) {
            firstPanel.setUnavailable(true);
            AirbnbAlertCreation priceAlert = new AirbnbAlertCreation();
            priceAlert.getInvalidPriceAlert();
        }
        else {
            firstPanel.setUnavailable(true);
        }
    }

    /**
     * Get minimum selected price from the user.
     * 
     * @param event of minumum price being selected in combo box.
     */
    public void getMinimumSelectedPrice(ActionEvent event) {

        ComboBox<Integer> minPrice = firstPanel.getMinPrice();
        
        Object selectedItem = minPrice.getSelectionModel().getSelectedItem();

        firstPanel.setSelectedMinPrice((int) selectedItem);

        //Check if the buttons can no longer be disabled or not.
        shouldButtonsBeDisabled();
        firstPanel.setFinalPriceRange();
    }

    /**
     * Get the maximum selected price from the user.
     * 
     * @param event of maximum price being selected in combo box.
     */
    public void getMaximumSelectedPrice(ActionEvent event) {

        ComboBox<Integer> maxPrice = firstPanel.getMaxPrice();

        Object selectedItem = maxPrice.getSelectionModel().getSelectedItem();

        firstPanel.setSelectedMaxPrice((int) selectedItem);

        //Check if the buttons can be disabled now, and set the final price range.
        shouldButtonsBeDisabled();
        firstPanel.setFinalPriceRange();
    }
    
    /**
     * run the main method to start the application.
     * @param args
     */
    public static void main(String[] args) {
        launch();    
    }

}


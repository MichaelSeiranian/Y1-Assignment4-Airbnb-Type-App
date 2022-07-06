import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import java.util.*;
import javafx.scene.input.MouseEvent;
import java.awt.image.*;
import javafx.scene.effect.ColorAdjust; 
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This is the fourth panel.
 * This panel shows a map of London with clickable icons on each borough opening a list of train stations there.
 *
 * @author Michael Seiranian - K20127931
 * @author William Atta - K21097986
 * @version 28/03/22
 */
public class UndergroundPanel {
    private Scene scene;
    private final String PREV = "<", NEXT = ">";

    //Two buttons to navigate the panels.
    private Button previousButton, nextButton;

    private Pane undergroundPane;
    
    private ArrayList<UndergroundStation> undergroundList = new UndergroundDataLoader().load();

    @FXML
    private ImageView harrow, hillington, ealing, hounslow, richmond, brent, barnet, enfield, 
    haringey, waltham, camden, islington, hackney, westminster, kensington, hammersmith, 
    kingston, merton, wandsworth, sutton, city, lambeth, croydon, southwark, tower,
    redbridge, newham, barking, greenwich, lewisham, bromley, bexley, havering;
    
    @FXML private TableView tableView;
    private TableColumn<UndergroundStation, String> stationClmn, lineClmn, boroughClmn, zoneClmn, dateClmn, usageClmn;
    @FXML private Button BacktomapButton;
    
    /**
     * Initializer creating table columns, which will display underground stations and data about them
     */
    @FXML
    public void initialize() {
        tableView.setPlaceholder(new Label("No underground stations in this borough."));  
        stationClmn = new TableColumn<>("Name");
        stationClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lineClmn = new TableColumn<>("Line(s)");
        lineClmn.setCellValueFactory(new PropertyValueFactory<>("lines"));
        boroughClmn = new TableColumn<>("Borough");
        boroughClmn.setCellValueFactory(new PropertyValueFactory<>("neighbourhood"));
        zoneClmn = new TableColumn<>("Zone(s)");
        zoneClmn.setCellValueFactory(new PropertyValueFactory<>("zones"));
        dateClmn = new TableColumn<>("Date opened");
        dateClmn.setCellValueFactory(new PropertyValueFactory<>("opened"));
        usageClmn = new TableColumn<>("Usage");
        usageClmn.setCellValueFactory(new PropertyValueFactory<>("usage"));
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tableView.getColumns().addAll(stationClmn, lineClmn, boroughClmn, zoneClmn, dateClmn, usageClmn);
    }
    
    /**
     * A constructor creating navigator buttons.
     */
    public UndergroundPanel() {
        previousButton = new Button(PREV);
        nextButton = new Button(NEXT);

        previousButton.setId("navigationButtons");
        nextButton.setId("navigationButtons");
        
    }

    /**
     * This border pane positions, holds and sets the sizes of the navigator buttons.
     * 
     * @return the border pane
     */
    public BorderPane addBorderPane() {
        BorderPane borderPane = new BorderPane();

        borderPane.setId("borderPane");

        previousButton.setPrefSize(75,20);
        borderPane.setLeft(previousButton);

        nextButton.setPrefSize(75, 20);
        borderPane.setRight(nextButton);

        return borderPane;
    }

    /**
     * Loads Underground.fxml, creates and sets up the scene of the fourth panel.
     * 
     * @return the fourth panel
     * @throws IOException if the FXML file fails to load.
     */
    public Scene undergroundScene() throws java.io.IOException
    {     
        //Loading FXML file
        URL url = getClass().getResource("Underground.fxml");
        undergroundPane = FXMLLoader.load(url);

        //Creating the scene
        BorderPane undergroundBorderPane = new BorderPane();
        undergroundBorderPane.setCenter(undergroundPane);

        BorderPane buttonPane = addBorderPane();
        undergroundBorderPane.setBottom(buttonPane);

        scene = new Scene(undergroundBorderPane, 1200, 750);
        scene.getStylesheets().add("Panel_3_StyleSheet.css");

        undergroundBorderPane.setId("root");
        buttonPane.setId("borderPane");

        return scene;
    }

    /**
     * Making colour of pins darker upon mouse hover
     * 
     * @param event of mouse hovering over pin
     */
    @FXML
    public void pinHover(MouseEvent e)
    {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ImageView pin = (ImageView) ((Node) e.getSource());
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.3);
        pin.setEffect(colorAdjust);
        stage.show();
    }

    /**
     * Returning pin colour to normal when mouse leaves pin
     * 
     * @param event of mouse no longer hovering pin
     */
    @FXML
    public void pinHoverExit(MouseEvent e)
    {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ImageView pin = (ImageView) ((Node) e.getSource());
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        pin.setEffect(colorAdjust);
        stage.show();
    }
    
    /**
     * This method shows the list of underground stations in the borough of the clicked pin
     * 
     * @param event of clicked pin
     */
    @FXML
    public void pinClick(MouseEvent e)
    {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ImageView pin = (ImageView) ((Node) e.getSource());
        
        String name = pin.getId();
        for (int i = 0; i < undergroundList.size(); i++) {
            undergroundList.get(i);
            if(undergroundList.get(i).getNeighbourhood().toLowerCase().contains(name)){
                tableView.getItems().add(undergroundList.get(i));
            }
        }
        tableView.setVisible(true);
        BacktomapButton.setVisible(true);
        stage.show();
    }
    
    /**
     * Clears the table and returns back to the map
     * 
     * @param event of "Back to map" button being clicked
     */
    @FXML
    public void backtomapClick(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tableView.getItems().clear();
        tableView.setVisible(false);
        BacktomapButton.setVisible(false);
        stage.show();
    }
    
    /**
      Below are accessor methods for the AirbnbApplication class
     */
    public Button getNextButton() {
        return nextButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }
}

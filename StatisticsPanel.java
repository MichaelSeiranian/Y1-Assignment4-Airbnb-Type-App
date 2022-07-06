import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.*;
import java.util.HashMap;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This is the third statistics panel.
 * This panel displays some statistics related to the airbnb-london 
 * and underground-stations CSV datasets.
 * 
 * @author Michael Seiranian - K20127931
 * @author William Atta - K21097986
 * @version 28/03/22
 */
public class StatisticsPanel {
    private Scene scene;
    private final String PREV = "<", NEXT = ">";

    //Two buttons to navigate the panels.
    private Button previousButton, nextButton;

    private Pane statPane;
    //loads the two CSV files
    private ArrayList<AirbnbListing> list = new AirbnbDataLoader().load();
    private ArrayList<UndergroundStation> undergroundList = new UndergroundDataLoader().load();
    //HashMap to store borough name and number of listings in the borough
    private HashMap<String, Integer> numListingsPerBorough = numListingsPerBorough();
    //Label fields that will display statistics data.
    @FXML private Label boxOneName, boxOneValue, boxTwoName, boxTwoValue, boxThreeName, boxThreeValue, boxFourName, boxFourValue;

    private boolean tempfound = false;
    //Creating individual statistics as a name, value pair, and setting whether they are initially visible
    private Statistic avgReviewsStat = new Statistic("Average number of reviews", avgReviews(),true);
    private Statistic totalPropertiesStat = new Statistic("Total number of properties", totalProperties(),true);
    private Statistic homesAndApartmentsStat = new Statistic("Number of homes and apartments only", homesAndApartments(),true);
    private Statistic mostExpensiveBoroughStat = new Statistic("Most expensive borough", mostExpensiveBorough(),true);
    private Statistic mostReviewsBoroughStat = new Statistic("Borough with most reviews",mostReviewsBorough(),false);
    private Statistic gardenListingsStat = new Statistic("Number of properties with a garden",gardenListings(),false);
    private Statistic mostLuxuriousBoroughStat = new Statistic("Most luxurious borough",mostLuxuriousBorough(),false);
    private Statistic mostStationsBoroughStat = new Statistic("Borough with the most underground stations", mostStationsBorough(),false);
    private List<Statistic> statisticsList = new ArrayList<Statistic>();
    private int statCount = 0;
    private int statCount2 = 1;
    private int statCount3 = 2;
    private int statCount4 = 3;

    /**
     * Setting initial statistics to be shown on panel
     * This method is automatically run after the constructor.
     */
    @FXML
    public void initialize() {
        boxOneName.setText(statisticsList.get(statCount).getName());
        boxOneValue.setText(statisticsList.get(statCount).getValue());
        boxTwoName.setText(statisticsList.get(statCount2).getName());
        boxTwoValue.setText(statisticsList.get(statCount2).getValue()); 
        boxThreeName.setText(statisticsList.get(statCount3).getName());
        boxThreeValue.setText(statisticsList.get(statCount3).getValue());
        boxFourName.setText(statisticsList.get(statCount4).getName());
        boxFourValue.setText(statisticsList.get(statCount4).getValue());
    }
    
    /**
     * Constructor creates navigator buttons, sets their CSS IDs and adds statistics to a list
     */
    public StatisticsPanel() {
        this.statPane = statPane;
        previousButton = new Button(PREV);
        nextButton = new Button(NEXT);
        previousButton.setId("navigationButtons");
        nextButton.setId("navigationButtons");
        statisticsList.add(avgReviewsStat);
        statisticsList.add(totalPropertiesStat);
        statisticsList.add(homesAndApartmentsStat);
        statisticsList.add(mostExpensiveBoroughStat);
        statisticsList.add(mostReviewsBoroughStat);
        statisticsList.add(gardenListingsStat);
        statisticsList.add(mostLuxuriousBoroughStat);
        statisticsList.add(mostStationsBoroughStat);
    }

    /**
     * Creates border pane storing the navigator buttons
     * 
     * @return The border pane containing the buttons
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
     * Creates and organises the entire scene of the statistics panel
     * 
     * @return The statistics scene
     */
    public Scene statScene() throws java.io.IOException
    {     
        //Loading FXML file
        URL url = getClass().getResource("Stats.fxml");
        statPane = FXMLLoader.load(url);

        //Creating the scene
        BorderPane statBorderPane = new BorderPane();
        statBorderPane.setCenter(statPane);

        BorderPane buttonPane = addBorderPane();
        statBorderPane.setBottom(buttonPane);

        scene = new Scene(statBorderPane, 1200, 750);
        scene.getStylesheets().add("Panel_3_StyleSheet.css");

        statBorderPane.setId("root");
        buttonPane.setId("borderPane");

        return scene;
    }

    /**
     * Finds next not showing stat and displays on the first stat box
     * 
     * @param First box next button pressed
     */
    @FXML
    private void nextPressed1(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount).setFalse();
        boolean found = false;
        while(found == false){      //finds next not showing stat
            if(statCount < statisticsList.size()-1){
                statCount++;
            }
            else{
                statCount = 0;
            }
            if(!statisticsList.get(statCount).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount).setTrue();
        boxOneName.setText(statisticsList.get(statCount).getName());
        boxOneValue.setText(statisticsList.get(statCount).getValue());  
        stage.show();
    }
    
    /**
     * Finds previous not showing stat and displays on the first stat box
     *
     * @param First box previous button pressed
     */
    @FXML
    private void previousPressed1(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount).setFalse(); //sets current stat to not showing
        boolean found = false;
        while(found == false){
            if(statCount > 0){
                statCount--;
            }
            else{
                statCount = statisticsList.size()-1;
            }
            if(!statisticsList.get(statCount).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount).setTrue(); //sets new stat to showing
        boxOneName.setText(statisticsList.get(statCount).getName());
        boxOneValue.setText(statisticsList.get(statCount).getValue());  
        stage.show();
    }
    
    /**
     * Finds next not showing stat and displays on the second stat box
     *
     * @param Second box next button pressed
     */
    @FXML
    private void nextPressed2(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount2).setFalse();
        boolean found = false;
        while(found == false){      //finds next not showing stat
            if(statCount2 < statisticsList.size()-1){
                statCount2++;
            }
            else{
                statCount2 = 0;
            }
            if(!statisticsList.get(statCount2).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount2).setTrue();
        boxTwoName.setText(statisticsList.get(statCount2).getName());
        boxTwoValue.setText(statisticsList.get(statCount2).getValue());  
        stage.show();
    }

    /**
     * Finds previous not showing stat and displays on the second stat box
     *
     * @param Second box previous button pressed
     */
    @FXML
    private void previousPressed2(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount2).setFalse();
        boolean found = false;
        while(found == false){
            if(statCount2 > 0){
                statCount2--;
            }
            else{
                statCount2 = statisticsList.size()-1;
            }
            if(!statisticsList.get(statCount2).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount2).setTrue();
        boxTwoName.setText(statisticsList.get(statCount2).getName());
        boxTwoValue.setText(statisticsList.get(statCount2).getValue());  
        stage.show();
    }

    /**
     * Finds next not showing stat and displays on the third stat box
     *
     * @param Third box next button pressed
     */
    @FXML
    private void nextPressed3(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount3).setFalse();
        boolean found = false;
        while(found == false){      //finds next not showing stat
            if(statCount3 < statisticsList.size()-1){
                statCount3++;
            }
            else{
                statCount3 = 0;
            }
            if(!statisticsList.get(statCount3).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount3).setTrue();
        boxThreeName.setText(statisticsList.get(statCount3).getName());
        boxThreeValue.setText(statisticsList.get(statCount3).getValue());  
        stage.show();
    }

    /**
     * Finds previous not showing stat and displays on the third stat box
     *
     * @param Third box previous button pressed
     */
    @FXML
    private void previousPressed3(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount3).setFalse();
        boolean found = false;
        while(found == false){
            if(statCount3 > 0){
                statCount3--;
            }
            else{
                statCount3 = statisticsList.size()-1;
            }
            if(!statisticsList.get(statCount3).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount3).setTrue();
        boxThreeName.setText(statisticsList.get(statCount3).getName());
        boxThreeValue.setText(statisticsList.get(statCount3).getValue());  
        stage.show();
    }

    /**
     * Finds next not showing stat and displays on the fourth stat box
     *
     * @param Fourth box next button pressed
     */
    @FXML
    private void nextPressed4(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount4).setFalse();
        boolean found = false;
        while(found == false){      //finds next not showing stat
            if(statCount4 < statisticsList.size()-1){
                statCount4++;
            }
            else{
                statCount4 = 0;
            }
            if(!statisticsList.get(statCount4).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount4).setTrue();
        boxFourName.setText(statisticsList.get(statCount4).getName());
        boxFourValue.setText(statisticsList.get(statCount4).getValue());  
        stage.show();
    }

    /**
     * Finds previous not showing stat and displays on the fourth stat box
     *
     * @param Fourth box previous button pressed
     */
    @FXML
    private void previousPressed4(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        statisticsList.get(statCount4).setFalse();
        boolean found = false;
        while(found == false) {
            if(statCount4 > 0){
                statCount4--;
            }
            else{
                statCount4 = statisticsList.size()-1;
            }
            if(!statisticsList.get(statCount4).getShowing()){
                found = true;
            }
        }
        statisticsList.get(statCount4).setTrue();
        boxFourName.setText(statisticsList.get(statCount4).getName());
        boxFourValue.setText(statisticsList.get(statCount4).getValue());  
        stage.show();
    }

    /**
     * Calculates average reviews per property by diving the number of all reviews 
     * by the number of properties
     *
     * @return String of the average reviews per property integer to be displayed as a stat
     */
    private String avgReviews() {
        int total = 0;
        for(int i=0; i<list.size(); i++){
            total = total + list.get(i).getNumberOfReviews();
        }
        return "" + total/list.size();
    }

    /**
     * Returns total number of properties
     *
     * @return String of the total number of properties integer to be displayed as a stat
     */
    private String totalProperties() {
        return "" + list.size();
    }

    /**
     * Counts number of homes and apartments only
     *
     * @return String of the number of homes and apartments to be displayed as a stat
     */
    private String homesAndApartments() {
        int count = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getRoom_type().equals("Entire home/apt")){
                count++;
            }
        }
        return "" + count;
    }
    
    /**
     * This method counts the number of properties each borough has and stores this data in a HashMap
     *
     * @return HashMap, key as borough, value as number of properties in that borough
     */
    private HashMap<String, Integer> numListingsPerBorough() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int tempCount;
        for(int i=0; i<list.size(); i++){
            if(hashMap.get(list.get(i).getNeighbourhood())==null) {
                tempCount = 0;
            }else{
                tempCount = hashMap.get(list.get(i).getNeighbourhood());
            }
            hashMap.put(list.get(i).getNeighbourhood(),tempCount+1);
        }
        return(hashMap);
    }

    /**
     * Finds most expensive borough by dividing the total price * minimum nights of each
     * listing in each borough by the number of listings in that borough and finding the 
     * borough with the highest average price per listing
     *
     * @return String the most expensive borough
     */
    private String mostExpensiveBorough() {
        int tempTotal;
        HashMap<String, Double> avgPricePerListing = new HashMap<>();
        HashMap<String, Integer> totalPrice = new HashMap<>(); //HashMap will store borough name and total price
        for(int i=0; i<list.size(); i++){       //counting the total price with min nights of all properties in each borough and storing in HashMaps
            if(totalPrice.get(list.get(i).getNeighbourhood())==null) {
                tempTotal = 0;
            }else{
                tempTotal = totalPrice.get(list.get(i).getNeighbourhood());
            }
            totalPrice.put(list.get(i).getNeighbourhood(),tempTotal+(list.get(i).getPrice()*list.get(i).getMinimumNights()));
        }    
        return findMax(hashMapAveraging(totalPrice));
    }
    
    /**
     * Finds borough with most reviews per listing
     *
     * @return String name of borough with most reviews
     */
    private String mostReviewsBorough() {
        int tempTotal;
        HashMap<String, Integer> totalReviewsPerBorough = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            if(totalReviewsPerBorough.get(list.get(i).getNeighbourhood())==null) {
                tempTotal = 0;
            }else{
                tempTotal = totalReviewsPerBorough.get(list.get(i).getNeighbourhood());
            }
            totalReviewsPerBorough.put(list.get(i).getNeighbourhood(), tempTotal + list.get(i).getNumberOfReviews());
        }
        return findMax(hashMapAveraging(totalReviewsPerBorough));
    }

    /**
     * Counts number of listings with a garden by looking if the description of
     * each listing contains the word "garden"
     *
     * @return String of the number of listings that contain a garden
     */
    private String gardenListings(){
        int count = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getName().toLowerCase().contains("garden")){
                count++;
            }
        }
        return ""+count;
    }
    
    /**
     * Finds the most luxurious borough by seeing which borough contains
     * the most listings with words "luxury" or "luxurious" per listing
     *
     * @return String of the most luxurious borough
     */
    private String mostLuxuriousBorough(){
        int tempCount;
        HashMap<String, Integer> luxListings = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            String name = list.get(i).getName().toLowerCase();
            if(name.contains("luxurious") || name.contains("luxury")){
                if(luxListings.get(list.get(i).getNeighbourhood())==null) {
                    tempCount = 0;
                }else{
                    tempCount = luxListings.get(list.get(i).getNeighbourhood());
                }
                luxListings.put(list.get(i).getNeighbourhood(),tempCount+1);
            }
        }
        return findMax(hashMapAveraging(luxListings));
    }
    
    /**
     * Finds which borough has the most stations
     *
     * @return String of borough with the most stations
     */
    private String mostStationsBorough() {
        double tempCount;
        HashMap<String, Double> countList = new HashMap<>();
        for(int i = 0; i < undergroundList.size(); i++){
            if(countList.get(undergroundList.get(i).getNeighbourhood())==null) {
                tempCount = 0;
            }else{
                tempCount = countList.get(undergroundList.get(i).getNeighbourhood());
            }
            countList.put(undergroundList.get(i).getNeighbourhood(),tempCount+1);
        }
        return findMax(countList);
    }
    
    /**
     * Finds the key of the highest value in HashMap
     *
     * @param HashMap to find key of highest value
     * @return String key of HashMap with highest value
     */
    private String findMax(HashMap<String, Double> avgList){
        double max = 0;
        for (double value : avgList.values()) {
            if(value > max){
                max = value;
            }
        }
        String key = new String();
        for (Map.Entry<String, Double> entry : avgList.entrySet()) {
            if (entry.getValue()==max) {
                key = entry.getKey();
            }
        }
        return key;
    }
    
    /**
     * Takes the HashMap with values of some calculated attribute and divides the values
     * with the number of listings in that borough, to give an average of the attribute per listing 
     * per borough.
     * 
     * @param HashMap containing previously calculated attributes as value about each borough
     * @return HashMap the borough as key, and the average as value
     */
    private HashMap<String, Double> hashMapAveraging(HashMap<String, Integer> totalList){
        HashMap<String, Double> avgList = new HashMap<>();
        for (Map.Entry<String, Integer> entry : numListingsPerBorough.entrySet()) {
            String key = entry.getKey();
            int count1 = entry.getValue();
            int count2 = totalList.get(key);
            double d = (double) count2/(double) count1;
            avgList.put(key, d);
        }
        return avgList;
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

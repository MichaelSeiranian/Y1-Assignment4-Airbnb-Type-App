import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import javafx.scene.control.Button;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class deals with creating the colours to populate
 * the second panel with.
 *
 * The darker the colour, the more properties there are and
 * vice versa.
 *
 * @author israfeel_ashraf K21008926
 * @author shakeeb_jumaan K21087021
 */
public class Colours {

    private AirbnbFilteredListings theListings;
    private ArrayList<String> colours = new ArrayList<>();

    private int max, min;

    /**
     * Constructor to set the colours to use and to get the filtered listings.
     */
    public Colours() {
        theListings = new AirbnbFilteredListings();
        setColours();
    }

    /**
     * A method to add the certain colours into the array list.
     */
    private void setColours() {
        colours.add("-fx-background-color: #e2cffc;");
        colours.add("-fx-background-color: #c7b1e6;");
        colours.add("-fx-background-color: #c09af5;");
        colours.add("-fx-background-color: #af82ed");
        colours.add("-fx-background-color: #9665db;");
        colours.add("-fx-background-color: #8752d1;");
        colours.add("-fx-background-color: #8d47ed;");
        colours.add("-fx-background-color: #7532d1;");
        colours.add("-fx-background-color: #5924a3;");
        colours.add("-fx-background-color: #3e1a70;");

    }

    /**
     * A method to return the boroughs key value dictionary.
     * @return the key value pairings from the filtered arraylist class.
     */
    private HashMap<String, String> getBoroughs() {
        HashMap<String, String> boroughs = theListings.createBoroughDictionary();
        return boroughs;
    }


    /**
     * A method to show the colours of each button in the second panel.
     *
     * @param getMinPrice The minimum price selected by the user.
     * @param getMaxPrice The maximum price selected by the user.
     * @param buttonList The list of buttons to be iterated over.
     */
    public void showColours(int getMinPrice, int getMaxPrice, Button[] buttonList) {

        HashMap<String, String> boroughsInLondon = getBoroughs();

        //Get the filtered listing based on price.
        ArrayList<AirbnbListing> filteredList = theListings.loadAirBnb(getMinPrice, getMaxPrice);

        //Get the number of properties according to the price range in each borough.
        ArrayList<Integer> numProperties = numProperties(buttonList, filteredList ,boroughsInLondon);

        //Get the maximum and minimum number of properties out of all the boroughs.
        setMax(Collections.max(numProperties)); setMin(Collections.min(numProperties));


        //Iterate over the buttons, and decided which colour each button should be.
        for (int i = 0; i < buttonList.length; i++) {
            decideColours(buttonList[i],numProperties.get(i));
        }
    }

    /**
     * This method obtains the number of properties in each borough, and adds all of them into an arrayList.
     * @param buttonList The list of buttons
     * @param filteredList the filtered list of properties, based only on price.
     * @param boroughsInLondon the key value dictionary
     * @return the number of propertie in each borough.
     */
    private ArrayList<Integer> numProperties(Button[] buttonList, ArrayList<AirbnbListing> filteredList, HashMap<String, String> boroughsInLondon) {

        ArrayList<Integer> numProperties = new ArrayList<>();

        //Iterate over each button, and get the borough name of the button using the dictionary.
        for (int i = 0; i < buttonList.length; i++) {
            String key = buttonList[i].getText();

            //Create a stream that counts the number of properties in this borough.
            int numberOfProperties = (int) filteredList
                    .stream()
                    .filter(property -> (property.getNeighbourhood().equals(boroughsInLondon.get(key))))
                    .count();

            //Add this number to the arraylist.
            numProperties.add(numberOfProperties);
        }

        return numProperties;
    }

    /**
     * Compute the average number of properties, so a scaling effect of colours can be produced.
     * @return The interval per 10 steps to scale the colours accordingly.
     */
    private float getAverage() {
        float average;

        //Try to compute the difference between the maximum and minmum values.
        //If an arithmetic error occurs, the average will just be 0.
        try {
            average = getMax() - getMin();
            average = average / 10;
        } catch (ArithmeticException e) {
            average = 0;
        }

        return average;
    }

    /**
     *  This method decided the colour of each button.
     * @param button The button to choose the colour for.
     * @param numPropertiesInBorough The number of properties in this borough.
     */
    private void decideColours(Button button, int numPropertiesInBorough) {
        float increment = getAverage();
        int min2 = min;
        
        //If the increment is 0, (i.e an arithmetic error has occured, then set a different colour for it).
        if (increment == 0) {
            button.setStyle("-fx-background-color: #d1c8de;");
        }

        //Otherwise, depending on which one of the 10 intervals the button fits into, set its colour accordingly.
        else {
            if (numPropertiesInBorough <= (min2 + increment)) {             button.setStyle(colours.get(0)); }
            else if (numPropertiesInBorough <= (min2 + (2 * increment))) {  button.setStyle(colours.get(1)); }
            else if (numPropertiesInBorough <= (min2 + (3 * increment))) {  button.setStyle(colours.get(2)); }
            else if (numPropertiesInBorough <= (min2 + (4 * increment))) {  button.setStyle(colours.get(3)); }
            else if (numPropertiesInBorough <= (min2 + (5 * increment))) {  button.setStyle(colours.get(4)); }
            else if (numPropertiesInBorough <= (min2 + (6 * increment))) {  button.setStyle(colours.get(5)); }
            else if (numPropertiesInBorough <= (min2 + (7 * increment))) {  button.setStyle(colours.get(6)); }
            else if (numPropertiesInBorough <= (min2 + (8 * increment))) {  button.setStyle(colours.get(7)); }
            else if (numPropertiesInBorough <= (min2 + (9 * increment))) {  button.setStyle(colours.get(8)); }
            else if (numPropertiesInBorough <= (min2 + (10 * increment))) { button.setStyle(colours.get(9)); }
        }
    }

    /*
      The methods below are accessor and mutator methods.
     */

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    public float average() {
        return getAverage();
    }
    
    public ArrayList<String> getColours() {
        return colours;
    }
}

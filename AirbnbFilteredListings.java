import javafx.scene.control.Button;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class deals with creating filtered listings of the airbnb dataset.
 * This class filters the dataset based on price, and provides a key value dictionary
 * to map the buttons to the locations within the dataset.
 *
 * @author israfeel_ashraf K21008936
 * @version 22/03/22
 */
public class AirbnbFilteredListings {

    private HashMap<String, String> neighbourhoodLookUp;

    /**
     * A constructor to set up the key value dictionary.
     */
    public AirbnbFilteredListings() {
        neighbourhoodLookUp = new HashMap<>();
        createBoroughDictionary();
    }

    /**
     * A method to return the key value dictionary so other classes can use it.
     * @return The key value dictionary of codes to locations.
     */
    public HashMap<String, String> createBoroughDictionary() {

        neighbourhoodLookUp.put("ENFI", "Enfield");              neighbourhoodLookUp.put("BARN", "Barnet");
        neighbourhoodLookUp.put("HRGY", "Haringey");            neighbourhoodLookUp.put("WALT", "Waltham Forest");
        neighbourhoodLookUp.put("HRRW", "Harrow");              neighbourhoodLookUp.put("BREN", "Brent");
        neighbourhoodLookUp.put("CAMD", "Camden");              neighbourhoodLookUp.put("ISLI", "Islington");
        neighbourhoodLookUp.put("HACK", "Hackney");             neighbourhoodLookUp.put("REDB", "Redbridge");
        neighbourhoodLookUp.put("HAVE", "Havering");            neighbourhoodLookUp.put("HILL", "Hillingdon");
        neighbourhoodLookUp.put("EALI", "Ealing");              neighbourhoodLookUp.put("KENS", "Kensington and Chelsea");
        neighbourhoodLookUp.put("WSTM", "Westminster");         neighbourhoodLookUp.put("TOWH", "Tower Hamlets");
        neighbourhoodLookUp.put("NEWH", "Newham");              neighbourhoodLookUp.put("BARK", "Barking and Dagenham");
        neighbourhoodLookUp.put("HOUN", "Hounslow");            neighbourhoodLookUp.put("HAMM", "Hammersmith and Fulham");
        neighbourhoodLookUp.put("WAND", "Wandsworth");          neighbourhoodLookUp.put("CITY", "City of London");
        neighbourhoodLookUp.put("GWCH", "Greenwich");           neighbourhoodLookUp.put("BEXL", "Bexley");
        neighbourhoodLookUp.put("RICH", "Richmond upon Thames"); neighbourhoodLookUp.put("MERT", "Merton");
        neighbourhoodLookUp.put("LAMB", "Lambeth");             neighbourhoodLookUp.put("STHW", "Southwark");
        neighbourhoodLookUp.put("LEWS", "Lewisham");            neighbourhoodLookUp.put("KING", "Kingston upon Thames");
        neighbourhoodLookUp.put("SUTT","Sutton");               neighbourhoodLookUp.put("CROY","Croydon");
        neighbourhoodLookUp.put("BROM","Bromley");


        return neighbourhoodLookUp;
    }


    /**
     * A method to obtain the listings based on the user selction price.
     * @param minPrice The min price selected by the user.
     * @param maxPrice The maximum price selected by the user.
     * @return The filtered listings.
     */
    public ArrayList<AirbnbListing> loadAirBnb(int minPrice, int maxPrice) {
        AirbnbDataLoader air = new AirbnbDataLoader();
        ArrayList<AirbnbListing> listOfRecords = air.load();

        //Create a stream that stores the returned properties into an arrayList.
        ArrayList<AirbnbListing> filteredListings = (ArrayList<AirbnbListing>) listOfRecords
                .stream()
                .filter(property -> ((property.getPrice() >= minPrice)) && (property.getPrice() <= maxPrice))
                .collect(Collectors.toList());

        return filteredListings;
    }

    /**
     * A method to load the airbnb listings based on price, but also on borough location.
     * @param minPrice the min price selected by the user.
     * @param maxPrice the max price selected by the user.
     * @param button The button to get the location from
     * @return The filtered arraylist
     */
    public ArrayList<AirbnbListing> getBoroughListings(int minPrice, int maxPrice, Button button) {
        ArrayList<AirbnbListing> priceListings = loadAirBnb(minPrice, maxPrice);

        //Create the filterd arraylist using a stream, which checks if the neighbourhood is the same as the
        //value obtain from the button code.
        ArrayList<AirbnbListing> filteredBoroughListings = (ArrayList<AirbnbListing>) priceListings
                .stream()
                .filter(property -> (property.getNeighbourhood().equals(neighbourhoodLookUp.get(button.getText()))))
                .collect(Collectors.toList());

        return filteredBoroughListings;
    }
}

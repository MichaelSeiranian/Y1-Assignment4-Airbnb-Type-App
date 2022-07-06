import java.util.ArrayList;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * A sorting types class, in order to sort the borough listings,
 * based on the host name, the price and the number of reviews.
 *
 * @author israfeel_ashraf - K21008936
 * @version 22/03/22
 */
public abstract class SortingTypes {

    /**
     * Abstract method to sort the list.
     * @param listToBeSorted The list which will be ordered.
     * @return The sorted list.
     */
    public abstract ArrayList<AirbnbListing> sort(ArrayList<AirbnbListing> listToBeSorted);

}

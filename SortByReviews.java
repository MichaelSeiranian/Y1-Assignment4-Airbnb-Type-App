import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 *  A class which sorts by the number of reviews.
 *
 * @author israfeel_ashraf - K21008936
 * @version 22/03/22
 */
public class SortByReviews extends SortingTypes {
    /**
     * A method to return a sorted list based on the number of reviews.
     * @param listToBeSorted The list which will be ordered.
     * @return The sorted list based on the number of reviews.
     */
    @Override
    public ArrayList<AirbnbListing> sort(ArrayList<AirbnbListing> listToBeSorted) {

        //Create a stream which sorts the input list by the number of reviews.
        ArrayList<AirbnbListing> sortedList = (ArrayList<AirbnbListing>) listToBeSorted
                .stream()
                .sorted(Comparator.comparing(AirbnbListing::getNumberOfReviews))
                .collect(Collectors.toList());

        return sortedList;
    }
}
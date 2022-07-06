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
 * A class to sort by price.
 *
 * @author israfeel_ashraf - K21008936
 * @version 22/03/22
 */
public class SortByPrice extends SortingTypes {
    /**
     * A method to sort by price
     * @param listToBeSorted The list which will be ordered.
     * @return the sorted list.
     */
    @Override
    public ArrayList<AirbnbListing> sort(ArrayList<AirbnbListing> listToBeSorted) {


        //Stream to sort by price.
        ArrayList<AirbnbListing> sortedList = (ArrayList<AirbnbListing>) listToBeSorted
                .stream()
                .sorted(Comparator.comparing(AirbnbListing::getPrice))
                .collect(Collectors.toList());

        return sortedList;
    }
}

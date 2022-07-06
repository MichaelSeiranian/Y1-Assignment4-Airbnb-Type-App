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
 * A sort by host name class, which will sort the properties
 * of the selected borough by the host name.
 *
 * @author israfeel_ashraf - K21008936
 * @version 22/03/22
 */
public class SortByHostName extends SortingTypes {
    /**
     * A method to take in a list and return a sorted list based on host name
     * @param listToBeSorted The list which will be ordered.
     * @return the list that is now sorted via host name.
     */
    @Override
    public ArrayList<AirbnbListing> sort(ArrayList<AirbnbListing> listToBeSorted) {

        // Create a stream, which sorts the list via hostname, and then adds to a new list.
        ArrayList<AirbnbListing> sortedList = (ArrayList<AirbnbListing>) listToBeSorted
                .stream()
                .sorted(Comparator.comparing(AirbnbListing::getHost_name))
                .collect(Collectors.toList());

        return sortedList;
    }
}

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;

/** 
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class is responsible for loading the undergroun-stations.csv file
 * and organizing the data into an ArrayList
 * 
 * @author Michael Seiranian - K20127931
 * @version 26/03/22
 */
public class UndergroundDataLoader {
    /** 
     * Return an ArrayList containing the rows in the underground stations csv file.
     */
    public ArrayList<UndergroundStation> load() {
        ArrayList<UndergroundStation> stations = new ArrayList<UndergroundStation>();
        try{
            URL url = getClass().getResource("underground-stations.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String name = line[0];
                String lines = line[1];
                String neighbourhood = line[2];
                String zones = line[3];
                String opened = line[4];
                String usage = line[5];
                

                UndergroundStation station = new UndergroundStation(name, lines, neighbourhood, zones, opened, usage);
                stations.add(station);
            }
        } catch(IOException | URISyntaxException e){
            System.out.println("Failure! File couldn't be loaded");
            e.printStackTrace();
        }
        System.out.println("Success! Number of loaded stations: " + stations.size());
        return stations;
    }
}

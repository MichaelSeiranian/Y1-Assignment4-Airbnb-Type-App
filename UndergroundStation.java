/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986

 * This class represents one underground station.
 * Each column in the underground-station.csv file has a field in this class
 * 
 * @author Michael Seiranian - K20127931
 * @version 26/03/22
 */ 

public class UndergroundStation {
    //Name of underground station
    private String name;
    //Lines that go through the station
    private String lines;
    //Neighbourhood/borough the station is in
    private String neighbourhood;
    //Zone(s) that the station is in
    private String zones;
    //Date the station was opened
    private String opened;
    //Usage of the station
    private String usage;

    /**
     * Constructor class, representing one underground station
     * 
     * @param All parameters explained in field declarations
     */
    public UndergroundStation(String name, String lines, String neighbourhood, String zones, String opened, String usage) {
        this.name = name;
        this.lines = lines;
        this.neighbourhood = neighbourhood;
        this.zones = zones;
        this.opened = opened;
        this.usage = usage;
    }

    /**
     * Below are accessor methods to retrieve information about an underground station 
     * to be used throughout the application.
     */
    public String getName() {
        return name;
    }

    public String getLines() {
        return lines;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getZones() {
        return zones;
    }

    public String getOpened() {
        return opened;
    }

    public String getUsage() {
        return usage;
    }
}

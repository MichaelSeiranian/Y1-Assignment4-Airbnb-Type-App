/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * This class will hold information about each specific statistic to be displayed
 * on the statistics panel
 *
 * @author Michael Seiranian K20127931
 * @author William Atta K21097986
 * @version 25/03/22
 */
public class Statistic
{
    private String name;
    private String value;
    private boolean showing;

    /**
     * Constructor for objects of class Statistic
     * 
     * @param name of statistic
     * @param value of statistic
     * @param boolean value to keep track of if the statistic is showing or not
     */
    public Statistic(String name, String value, boolean showing)
    {
        this.name = name;
        this.value = value;
        this.showing = showing;
    }

    /**
     * @return name of statistic
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return value of statistic
     */
    public String getValue()
    {
        return value;
    }
    
    /**
     * @return if statistic is showing
     */
    public boolean getShowing()
    {
        return showing;
    }
    
    /**
     * Sets showing to true
     */
    public void setTrue()
    {
        showing = true;
    }

    /**
     * Sets showing to false
     */
    public void setFalse()
    {
        showing = false;
    }
    
}

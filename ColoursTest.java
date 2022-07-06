import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Group SWIM:
 *  1: Israfeel Ashraf  K21008936
 *  2: Shakeeb Jumaan   K21087021
 *  3: Michael Seiranian K20127931
 *  4: William Atta     K21097986
 *  
 * The test class ColoursTest. This test class performs all the necessary
 * unit tests for the functionality provided by the 'Colours' class.
 *
 * @author  Israfeel Ashraf K21008936
 * @version 28/03/22
 */
public class ColoursTest
{
    /**
     * Default constructor for test class ColoursTest
     */
    public ColoursTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Sets the minimum number of properties based on the user price range.
     */
    @Test
    public void testMin() {
        Colours testColours = new Colours();
        testColours.setMin(0);
        assertEquals(0, testColours.getMin());    
    }
    
    /**
     * Sets the maximum number of properties based on the user price range.
     */
    @Test
    public void testMax() {
        Colours testColours = new Colours();
        testColours.setMax(100);
        assertEquals(100, testColours.getMax());
    }
    
    /**
     * Tests whether it is possible to use the colour scheme to show the density of properties
     * in the user price range, when the difference between the max and min number of properties is 0.
     */
    @Test 
    public void testAverage_forError() {
        Colours testColours = new Colours();
        testColours.setMax(0);
        testColours.setMin(0);
        
        testColours.average();
        
        assertEquals(0, testColours.average());
    }
    
    /**
     * Tests whether it is possible to use the colour scheme to show the density of properties
     * in the user price range, when there is a difference between the max and min number of properties.
     * 
     * If there is, there will be 10 different colours that are assigned to each button to produce a gradient effect
     * to show the density of properties per borough.
     */
    @Test 
    public void testAverage_withoutError() {
        Colours testColours = new Colours();
        testColours.setMax(1000);
        testColours.setMin(19);
        
        testColours.average();
        
        assertEquals(((float) (98.1)), testColours.average());
    }
    
    /**
     * This test contains some code for testing, as the test class cannot intialise the javafx button
     * to pass in as a parameter for the actual method.
     * 
     * This test deals with the average error, when it is 0, and what colours the properties should be according to the value.
     */
    @Test 
    public void testToDecideColoursWhenThereIsAnErrorWithTheAverage() {
        Colours testColours = new Colours();
        
        testColours.setMax(0);
        testColours.setMin(0);
        
        String buttonColourString = "";
        
        float increment = testColours.average();
        if (increment == 0) {
            buttonColourString = "-fx-background-color: #d1c8de;";
        } else {
            buttonColourString = "other colour";
        }
        
        assertEquals(buttonColourString = "-fx-background-color: #d1c8de;", buttonColourString);
    }
    
    /**
     * A test to see what value is returned when there is no error with the average.
     */
    @Test 
    public void testToDecideColoursWhenThereIsNoErrorWithTheAverage() {
        Colours testColours = new Colours();
        
        testColours.setMax(100);
        testColours.setMin(0);
        
        String buttonColourString = "";
        int numPropertiesInBorough = 100;
        
        float increment = testColours.average();
        
        if (increment == 0) {
            buttonColourString = "-fx-background-color: #d1c8de;";
        } else {
            if (numPropertiesInBorough <= ((10 * increment) + testColours.getMin())) { buttonColourString = testColours.getColours().get(9); }
            else { buttonColourString = "otherColour"; }
        }
        
        assertEquals(buttonColourString = "-fx-background-color: #3e1a70;", buttonColourString);
    }
    
    
    /**
     * As the test class cannot initilaise the javafx button, the actual method from the colours class cannot be used to decide the 
     * colour of each button.
     * 
     * Instead the code has been put in this method, and this method will be tested instead.
     * 
     * @param min - The min number of properties according to the user price range across the dataset.
     * @parm max - The max number of properties according to the user price range across the dataset.
     * @param testColours - The Colours class object to be tested.
     * @param numOfPropertiesInBorough - The number of properties according to the user price range, in one borough.
     * 
     * @return A string of what the button style should be set to.
     */
    public String decideWhatColourTheButtonShouldBe(int max, int min, Colours testColours, int numOfPropertiesInBorough) {
        testColours.setMax(max);
        testColours.setMin(min);
        
        String buttonColourString = "";
        int numPropertiesInBorough = numOfPropertiesInBorough;
        
        float increment = testColours.average();
        
        int min2 = testColours.getMin();
        
        if (increment == 0) {
            buttonColourString = "error with the average";
        }
        
        else {
            if (numPropertiesInBorough <= (min2 + increment)) {             buttonColourString = testColours.getColours().get(0); }
            else if (numPropertiesInBorough <= (min2 + (2 * increment))) {  buttonColourString = testColours.getColours().get(1); }
            else if (numPropertiesInBorough <= (min2 + (3 * increment))) {  buttonColourString = testColours.getColours().get(2); }
            else if (numPropertiesInBorough <= (min2 + (4 * increment))) {  buttonColourString = testColours.getColours().get(3); }
            else if (numPropertiesInBorough <= (min2 + (5 * increment))) {  buttonColourString = testColours.getColours().get(4); }
            else if (numPropertiesInBorough <= (min2 + (6 * increment))) {  buttonColourString = testColours.getColours().get(5); }
            else if (numPropertiesInBorough <= (min2 + (7 * increment))) {  buttonColourString = testColours.getColours().get(6); }
            else if (numPropertiesInBorough <= (min2 + (8 * increment))) {  buttonColourString = testColours.getColours().get(7); }
            else if (numPropertiesInBorough <= (min2 + (9 * increment))) {  buttonColourString = testColours.getColours().get(8); }
            else if (numPropertiesInBorough <= (min2 + (10 * increment))) { buttonColourString = testColours.getColours().get(9); }
        }
        
        return buttonColourString;
    }

    /**
     * This test will decide what colour the button should be, testing the boundary condition of when the number of properties in 
     * one borough is equal to the maximum number of properties across the dataset.
     */
    @Test
    public void getFirstColour() {
        Colours tColours = new Colours();
        
        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 100);
        
        assertEquals(tColours.getColours().get(9), colourStr);
    }
    
    /**
     * This test will decide what colour the button should be, testing another boundary condition of when the number of properties in 
     * one borough is on the second boundary of the gradient colour scheme, as the gradient scheme takes the max and the min number of
     * properties, and creates 10 different colours. Depending on the value of the number of properties of the borough, and which range it
     * fits into, it will be assigned a colour.
     */
    @Test
    public void getSecondColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 90);

        assertEquals(tColours.getColours().get(8), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the third boundary condition of when the number of properties in 
     * one borough is on the third boundary of the gradient colour scheme.
     */
    @Test
    public void getThirdColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 80);

        assertEquals(tColours.getColours().get(7), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the fourth boundary condition of when the number of properties in 
     * one borough is on the fourth boundary of the gradient colour scheme.
     */
    @Test
    public void getFourthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 70);

        assertEquals(tColours.getColours().get(6), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the fifth boundary condition of when the number of properties in 
     * one borough is on the fifth boundary of the gradient colour scheme.
     */
    @Test
    public void getFifthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 60);

        assertEquals(tColours.getColours().get(5), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the sixth boundary condition of when the number of properties in 
     * one borough is on the sixth boundary of the gradient colour scheme.
     */
    @Test
    public void getSixthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 50);

        assertEquals(tColours.getColours().get(4), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the seventh boundary condition of when the number of properties in 
     * one borough is on the seventh boundary of the gradient colour scheme.
     */
    @Test
    public void getSeventhColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 40);

        assertEquals(tColours.getColours().get(3), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the eighth boundary condition of when the number of properties in 
     * one borough is on the eighth boundary of the gradient colour scheme.
     */
    @Test
    public void getEighthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 30);

        assertEquals(tColours.getColours().get(2), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the ninth boundary condition of when the number of properties in 
     * one borough is on the ninth boundary of the gradient colour scheme.
     */
    @Test
    public void getNinthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 20);

        assertEquals(tColours.getColours().get(1), colourStr);
    }

    /**
     * This test will decide what colour the button should be, testing the tenth boundary condition of when the number of properties in 
     * one borough is on the tenth boundary of the gradient colour scheme.
     */
    @Test
    public void getTenthColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 10);

        assertEquals(tColours.getColours().get(0), colourStr);
    }
    
    /**
     * This test will decide what colour the button should be, testing a boundary condition, when the number of properties within a borough
     * is equal to the minimum number of properties across the dataset.
     */
    @Test
    public void getMinViableColour() {
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(100, 0, tColours, 0);

        assertEquals(tColours.getColours().get(0), colourStr);
    }
    
    
    /**
     * This test will decide what colour the button should be, testing the maximum boundary condition of when the number of properties in 
     * one borough is equal to the maximum number of properties across the dataset.
     */
    @Test
    public void getMaxViableColour() {
    
        Colours tColours = new Colours();

        String colourStr = decideWhatColourTheButtonShouldBe(542, 0, tColours, 542);

        assertEquals(tColours.getColours().get(9), colourStr);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}

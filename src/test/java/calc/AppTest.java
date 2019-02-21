package calc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        System.out.println("Testing power");
        //testPower(10,2);
        //testPower(-5,5);
        testPower(3.0,-5.0);
    }

    private void testPower(double base, double power) {
        // if power == 0 result will be 1, startValue is 1 since 0*X = 0
        double output = Math.pow(base,power);
        System.out.println(output);
        double result = 1.0;

        // a^-1 == (1/a)^1
        /*if (power < 0){
            base = 1 / base;
            power = -power;
        }*/

        // multiplies the value by itself power times
        for (int i = 0; i < power; i++){
            result *= base;
        }
        assertEquals(Math.pow(base,power),result);
    }


}

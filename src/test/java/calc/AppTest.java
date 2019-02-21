package calc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

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
        try {
            File input = new File("src/test/resources/numbers.txt");
            Scanner in = new Scanner(input);
            while (in.hasNextDouble()){

                double a = in.nextDouble();
                System.out.println("a is " + a);
                double b = in.nextDouble();
                System.out.println("b is " + b);
                double result = Math.pow(a,b);
                testPower(a,b,result);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testPower(double base, double power, double expected) {
        DecimalFormat df = new DecimalFormat("#.##########");
        // if power == 0 result will be 1, startValue is 1 since 0*X = 0
        double output = Math.pow(base,power);
        System.out.println(output);
        double result = 1.0;

        // a^-1 == (1/a)^1
        if (power < 0){
            base = 1 / base;
            power = -power;
        }

        // multiplies the value by itself power times
        for (int i = 0; i < power; i++){
            result *= base;
        }
        expected = Double.parseDouble(df.format(expected));
        result = Double.parseDouble(df.format(result));
        assertEquals(expected,result);


    }


}

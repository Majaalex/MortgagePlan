package calc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        numberTest();
        nameTest();
    }

    // Tests
    private void nameTest() {
        try {
            Path path = Paths.get("src/test/resources/names.txt");
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1);
            String currentLine;
            while ((currentLine = br.readLine()) != null){
                String b = br.readLine();
                testParseName(currentLine,b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void testParseName(String unParsed, String expected) {
        String parsed;

        parsed = unParsed.replaceAll("[-]{1}","");
        parsed = parsed.replaceAll("[-]{2,}","-");
        parsed = parsed.replaceAll("-$","");
        // https://stackoverflow.com/questions/1611979/remove-all-non-word-characters-from-a-string-in-java-leaving-accented-charact
        String regEx = "[^\\p{L}\\p{Nd}\\s]+";
        parsed = parsed.replaceAll(regEx,"");
        assertEquals(expected,parsed);
    }

    private void numberTest() {
        try {
            File input = new File("src/test/resources/numbers.txt");
            Scanner in = new Scanner(input);
            while (in.hasNextDouble()){
                double a = in.nextDouble();
                double b = in.nextDouble();
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

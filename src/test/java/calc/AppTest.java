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
        yearToMonthTest();
        monthlyPaymentTest();
    }

    //-------------------------------------------
    // UNIT TESTS
    //-------------------------------------------


    // U = Total Loan
    // b = monthly interest
    // p = number of payments
    private void monthlyPaymentTest() {
        DecimalFormat df = new DecimalFormat("#.#");
        double amount;
        double U = 1000;
        // yearly interest divided into 12 months into a % amount
        double b = 1.0 / 12 / 100;
        double p = 24;

        // amount = U*b * (1 + b)^p  /  [(1 + b)^p - 1]
        double firstValue = U * b * Math.pow(1 + b, p);
        double secondValue = Math.pow(1+b,p) - 1;
        amount =  firstValue / secondValue;
        amount = Double.parseDouble(df.format(amount));
        System.out.println("Tested monthly payment.");
        assertEquals(amount,42.1);
    }

    private void yearToMonthTest() {
        try {
            Path path = Paths.get("src/test/resources/year.txt");
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1);
            String currentLine;
            while ((currentLine = br.readLine()) != null){
                String b = br.readLine();
                calculateYearToMonth(currentLine,b);
            }
            System.out.println("Tested year conversion.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void calculateYearToMonth(String yearStr, String expectedMonths) {
        double year = Double.parseDouble(yearStr);
        double months = year * 12;
        double expected = Double.parseDouble(expectedMonths);
        assertEquals(months,expected);
    }


    private void nameTest() {
        try {
            Path path = Paths.get("src/test/resources/names.txt");
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1);
            String currentLine;
            while ((currentLine = br.readLine()) != null){
                String b = br.readLine();
                testParseName(currentLine,b);
            }
            System.out.println("Tested names.");

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
            System.out.println("Tested numbers.");

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

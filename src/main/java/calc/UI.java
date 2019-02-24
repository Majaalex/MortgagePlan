package calc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

class UI {

    void createUI(){
        Locale finnish = new Locale("fi","FI");
        DataHandler data = new DataHandler();
        ArrayList<Customer> customers = data.readFile();
        int i = 1;
        for (Customer customer : customers){
            System.out.println("****************************************************************************************************");
            System.out.println("Prospect " + i++ + ": " + customer.getName() +
                    " wants to borrow " + displayCurrency(finnish,customer.getLoanAmount()) +
                    " for a period of " + customer.getYearsToPay() +
                    " years and pay " + displayCurrency(finnish, calculateCustomerMonthlyPayment(customer))  + " each month.");
        }
    }

    //--------------------
    // Calculations

    // U = Total Loan
    // b = monthly interest
    // p = number of payments
      private double calculateCustomerMonthlyPayment(Customer customer){
        DecimalFormat df = new DecimalFormat("#.###");
        double amount;
        double U = customer.getLoanAmount();
          // yearly interest divided into 12 months into a % amount
        double b = customer.getInterestRate() / 12 / 100;
        double p = splitYearToMonths(customer.getYearsToPay());


        // amount = U*b * (1 + b)^p  /  [(1 + b)^p - 1]
        double firstValue = U * b * mathPower(1 + b, p);
        double secondValue = mathPower(1+b, p) - 1;
        amount =  firstValue / secondValue;
        return Double.parseDouble(df.format(amount));
    }

    private double splitYearToMonths(int years){
        double totalMonths = 0;
        totalMonths = years * 12;
        return totalMonths;
    }

    // Calculates the exponent of a base
    private double mathPower(double base, double power){
        // if power == 0 result will be 1, startValue is 1 since 0*X = 0
        double result = 1;

        // a^-1 == (1/a)^1
        if (power < 0){
            base = 1 / base;
            power = -power;
        }

        // Multiply everything together
        for (int i = 0; i < power; i++){
            result = result * base;
        }
        return result;
    }

    // Displayed a currency based on locale
    static private String displayCurrency(Locale currentLocale, double currencyAmount){
        // Gets the currency corresponding to the Locale input FI => EUR
        Currency currency = Currency.getInstance(currentLocale);
        // Create a formatter to format currency properly
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(currencyAmount) + currency.getCurrencyCode();
    }


}

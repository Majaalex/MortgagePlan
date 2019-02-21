package calc;

import java.text.DecimalFormat;
import java.util.ArrayList;

class UI {

    void createUI(){
        DataHandler data = new DataHandler();
        ArrayList<Customer> customers = data.readFile();
        int i = 1;
        for (Customer customer : customers){
            System.out.println("****************************************************************************************************");
            System.out.println("Prospect " + i++ + ": " + customer.getName() +
                    " wants to borrow " +  customer.getLoanAmount() +
                    "€ for a period of " + customer.getYearsToPay() +
                    " years and pay " + calculateCustomerMonthlyPayment(customer) + " each month.");
        }
    }



    //--------------------
    // Calculations


    // U = Total Loan
    // b = monthly interest
    // p = number of payments
      private double calculateCustomerMonthlyPayment(Customer customer){
        DecimalFormat df = new DecimalFormat("#.###");
        double amount = 0;
        double U = customer.getLoanAmount();
        double b = customer.getInterestRate() / 12 / 100;
        double p = splitYearToMonths(customer.getYearsToPay());
        // yearly interest divided into 12 months

        // amount = U*b * (1 + b)^p  /  [(1 + b)^p - 1]
        double firstValue = U * b * mathPower(1 + b, p);
        double secondValue = mathPower(1+b, p) - 1;
        amount =  firstValue / secondValue;
        return Double.parseDouble(df.format(amount));
    }
    // TODO: This will round the totalMonths ie 1.4 * 12 == 16.8
    private double splitYearToMonths(int years){
        double totalMonths = 0;
        totalMonths = years * 12;
        return totalMonths;
    }

    // Calculates the base to the power of power
    private double mathPower(double base, double power){
        // if power == 0 result will be 1, startValue is 1 since 0*X = 0
        double result = 1;

        // a^-1 == (1/a)^1
        if (power < 0){
            base = 1 / base;
            power = -power;
        }

        // multiplies the value by itself power times
        for (int i = 0; i < power; i++){
            result = result * base;
        }
        return result;
    }


}

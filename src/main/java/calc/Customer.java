package calc;

public class Customer {
    private String name;
    private double loanAmount;
    private double interestRate;
    private int yearsToPay;

    public Customer(String name, double loanAmount, double interestRate, int yearsToPay){
        setInterestRate(interestRate);
        setName(name);
        setLoanAmount(loanAmount);
        setYearsToPay(yearsToPay);
    }

    //--------------------
    // Getters
    String getName() {
        return name;
    }
    double getLoanAmount() {
        return loanAmount;
    }
    int getYearsToPay() {
        return yearsToPay;
    }
    double getInterestRate() {
        return interestRate;
    }


    //--------------------
    // Setters
    private void setYearsToPay(int yearsToPay) {
        this.yearsToPay = yearsToPay;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    private void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}

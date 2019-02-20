public class Customer {
    private String name;
    private double totalLoan;
    private double interestRate;
    private int years;

    public Customer(String name, double totalLoan, double interestRate, int years){
        setInterestRate(interestRate);
        setName(name);
        setTotalLoan(totalLoan);
        setYears(years);
    }

    //--------------------
    // Getters
    public String getName() {
        return name;
    }
    public  double getTotalLoan() {
        return totalLoan;
    }
    public int getYears() {
        return years;
    }
    public double getInterestRate() {
        return interestRate;
    }


    //--------------------
    // Setters
    private void setYears(int years) {
        this.years = years;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }
    private void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }



}

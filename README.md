# Mortgage Calculator

A simple app which takes info from a .txt and calculates the monthly payment to be made for a loan.

## Getting Started

### Prerequisites

* Maven (https://maven.apache.org/install.html)

### Installing

```
git clone https://github.com/Majaalex/MortgagePlan
mvn package
java -cp target/MortgageCalculator-1.0.jar calc.Main
```

## Running the tests

### Unit tests

Testing for name sanitizing and exponents without Math.pow()
Makes use of the .txt files under test/resources

```
""Eva"" => Eva

```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management, unit testing etc.


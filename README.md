# Mortgage Calculator

A simple app which takes info from a .txt and calculates the monthly payment to be made for a loan.

## Getting Started

### Prerequisites

* Maven (https://maven.apache.org/install.html)

### Installing and running

```
git clone https://github.com/Majaalex/MortgagePlan
mvn package
java -jar target/MortgageCalculator-1.0.jar calc.Main
```

## Running the tests
To run the tests in maven
```
mvn test
```

### Unit tests

Testing for name sanitizing and exponents without Math.pow()
Makes use of the .txt files under test/resources

```
""Eva"" => Eva
testPower(base, power, expected)
testPower(10,2,100) => OK
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management, unit testing etc.


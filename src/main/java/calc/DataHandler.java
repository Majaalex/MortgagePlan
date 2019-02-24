package calc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DataHandler {

    ArrayList<Customer> readFile(){
        ArrayList<Customer> customers = new ArrayList<>();
        List<String> fileContent = new ArrayList<>();
        String fileName = "prospects.txt";

        // Read file contents into a list
        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            fileContent = stream
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        readLinesAndAddCustomers(customers, fileContent);
        return customers;
    }

    private void readLinesAndAddCustomers(ArrayList<Customer> customers, List<String> fileContent) {
        String name;
        double loanAmount;
        double interestRate;
        int yearsToPay;
        Customer customer;

        //loop through each line in the .txt file
        for (String line : fileContent){
            String[] holder = line.split(",");
            // Skip the first line since it's just table names
            if (holder[0].equals("Customer")){
                continue;
            }
            // Firstname Lastname in the same String
            if (holder.length == 4){
                name = holder[0];
                name = sanitizeName(name);
                loanAmount = Double.parseDouble(holder[1]);
                interestRate = Double.parseDouble(holder[2]);
                yearsToPay = Integer.parseInt(holder[3]);
            }
            // Firstname Lastname separated
            else if (holder.length == 5){
                name = sanitizeName(holder[0]) + " " + sanitizeName(holder[1]);
                loanAmount = Double.parseDouble(holder[2]);
                interestRate = Double.parseDouble(holder[3]);
                yearsToPay = Integer.parseInt(holder[4]);
            }
            // If it is an incorrect size, skip it
            else continue;

            // Create an instance of Customer and add it to the ArrayList of customers
            customer = new Customer(name,loanAmount,interestRate,yearsToPay);
            customers.add(customer);
        }
    }

    // Cleans the names from unnecessary characters
    private String sanitizeName(String unParsed){
            String parsed;
            parsed = unParsed.replaceAll("[-]{1}","");
            parsed = parsed.replaceAll("[-]{2,}","-");
            parsed = parsed.replaceAll("-$","");
            // https://stackoverflow.com/questions/1611979/remove-all-non-word-characters-from-a-string-in-java-leaving-accented-charact
            String regEx = "[^\\p{L}\\p{Nd}\\s]+";
            parsed = parsed.replaceAll(regEx,"");
            return parsed;
    }
}
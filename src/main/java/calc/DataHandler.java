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
        List<String> list = new ArrayList<>();
        String fileName = "prospects.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            list = stream
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //for each line is the list
        readLinesAndAddCustomers(customers, list);
        return customers;
    }

    private void readLinesAndAddCustomers(ArrayList<Customer> customers, List<String> list) {
        String name;
        double loanAmount;
        double interestRate;
        int yearsToPay;
        for (String line : list){
            Customer customer;
            //Remove redundant chars such as #
            line = line.replace("\"","");
            //split the line into an array
            String[] holder = line.split(",");
            //if array has a size of 4, firstnmae lastname are in the same section
            if (holder[0].equals("Customer")){
                continue;
            }
            if (holder.length == 4){
                name = holder[0];
                name = parseName(name);
                loanAmount = Double.parseDouble(holder[1]);
                interestRate = Double.parseDouble(holder[2]);
                yearsToPay = Integer.parseInt(holder[3]);
            }
            //if array has a size of 5, firstname lastname are seperate
            else if (holder.length == 5){
                name = parseName(holder[0]) + " " + parseName(holder[1]);
                loanAmount = Double.parseDouble(holder[2]);
                interestRate = Double.parseDouble(holder[3]);
                yearsToPay = Integer.parseInt(holder[4]);
            }
            // If it is an incorrect size, skip it
            else continue;
            //clean up these values and then construct the customer as an instance of Customer
            //add the Customer to customers arraylist
            customer = new Customer(name,loanAmount,interestRate,yearsToPay);
            customers.add(customer);
        }
    }

    private String parseName(String unParsed){
            String parsed;

            parsed = unParsed.replaceAll("[-]{1}}","");
            parsed = parsed.replaceAll("[-]{2,}","-");
            parsed = parsed.replaceAll("-$","");
            String regEx = "[^a-zA-ZåäöÅÄÖéúíóáëüïêûîôâÉÚÍÓÁèùìòàÈÙÌÒÀËÜÏ\\s-]";

            parsed = parsed.replaceAll(regEx,"");
            return parsed;
    }
}
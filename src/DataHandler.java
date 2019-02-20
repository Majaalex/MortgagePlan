import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class DataHandler {

    ArrayList<Customer> readFile(){
        ArrayList<Customer> customers = new ArrayList<>();
        String fileName = "prospects.txt";
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            String fileText = oi.readUTF();

            fi.close();
            oi.close();
        } catch (FileNotFoundException e) {
            System.out.println("The program cannot find a file called '" + fileName + "'.");
        } catch (IOException e) {
            e.getMessage();
        }

        return customers;
    }
}
import java.util.*;
import java.util.Queue;
import java.util.Stack;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class CompleteStack {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        // Create an empty stack
        Stack<CustomerInformation> completeStack = new Stack<>();

        // create file reader to read input file
        BufferedReader in = new BufferedReader(new FileReader("CustomerList.txt"));

        // Counter queues for each counter
        Queue<CustomerInformation> counter1Queue = new LinkedList<>();
        Queue<CustomerInformation> counter2Queue = new LinkedList<>();
        Queue<CustomerInformation> counter3Queue = new LinkedList<>();

        // Assume you have the customerList containing customer information
        LinkedList<CustomerInformation> customerList = new LinkedList<>();

        CustomerInformation cust;
        ItemInformation item;

        String inData = null;

        while ((inData = in.readLine()) != null) {
            // open while
            StringTokenizer st = new StringTokenizer(inData, ";");
            String CustName = st.nextToken();
            String CustIC = st.nextToken();
            int CounterPaid = Integer.parseInt(st.nextToken());
            String ItemID = st.nextToken();
            String ItemName = st.nextToken();
            double ItemPrice = Double.parseDouble(st.nextToken());
            String DatePurchase = st.nextToken();

            // create customer object
            cust = new CustomerInformation(CustName, CustIC, CounterPaid);

            // create item object
            item = new ItemInformation(ItemID, ItemName, ItemPrice, DatePurchase);

            // Add customer and item to the customerList
            cust.addItem(item);
            customerList.add(cust);
        } // close while

        try {
            // Close the file reader
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Process each customer and add them to the respective queues based on the counterPaid value
        for (CustomerInformation customer : customerList) {
            if (customer.getCounterPaid() == 1) {
                counter1Queue.offer(customer);
            } else if (customer.getCounterPaid() == 2) {
                counter2Queue.offer(customer);
            } else {
                counter3Queue.offer(customer);
            }
        }

        int menu = 0;
        do { // open while
            System.out.println("Menu 1 - Display All Receipt");
            System.out.println("Menu 2 - Exit Application");

            // ask user to enter menu that they want
            String input = JOptionPane.showInputDialog("Enter menu");
            menu = Integer.parseInt(input);

            // display all receipts
            if (menu == 1) {
                System.out.println("*******  HYPERMARKETSTORE  ********");
                // Process customers at Counter 1
                System.out.println("--------------------- Counter 1 Receipt----------------------");
                while (!counter1Queue.isEmpty()) {
                    CustomerInformation customer = counter1Queue.poll(); // Remove the first customer from Counter 1 queue
                    completeStack.push(customer); // Store the paid customer in completeStack

                    // Display customer information and the amount purchased
                    System.out.println("Customer Name: " + customer.getCustName());
                    System.out.println("Customer IC: " + customer.getCustIC());
                    double totalAmountPaid = 0;
                    for (ItemInformation purchasedItem : customer.getPurchasedItems()) {
                        System.out.println("Item ID: " + purchasedItem.getItemID());
                        System.out.println("Item Name: " + purchasedItem.getItemName());
                        totalAmountPaid += purchasedItem.getItemPrice();
                    }
                    System.out.println("Total Amount Paid: " + totalAmountPaid);
                    System.out.println("=========================================================");
                }
                System.out.println("*******  HYPERMARKETSTORE  ********");
                // Process customers at Counter 2
                System.out.println("--------------------- Counter 2 Receipt----------------------");
                while (!counter2Queue.isEmpty()) {
                    CustomerInformation customer = counter2Queue.poll(); // Remove the first customer from Counter 2 queue
                    completeStack.push(customer); // Store the paid customer in completeStack

                    // Display customer information and the amount purchased
                    System.out.println("Customer Name: " + customer.getCustName());
                    System.out.println("Customer IC: " + customer.getCustIC());
                    double totalAmountPaid = 0;
                    for (ItemInformation purchasedItem : customer.getPurchasedItems()) {
                        System.out.println("Item ID: " + purchasedItem.getItemID());
                        System.out.println("Item Name: " + purchasedItem.getItemName());
                        totalAmountPaid += purchasedItem.getItemPrice();
                    }
                    System.out.println("Total Amount Paid: " + totalAmountPaid);
                    System.out.println("=========================================================");
                }
                System.out.println("*******  HYPERMARKETSTORE  ********");
                // Process customers at Counter 3
                System.out.println("--------------------- Counter 3 Receipt----------------------");

                while (!counter3Queue.isEmpty()) {
                    CustomerInformation customer = counter3Queue.poll(); // Remove the first customer from Counter 3 queue
                    completeStack.push(customer); // Store the paid customer in completeStack

                    // Display customer information and the amount purchased
                    System.out.println("Customer Name: " + customer.getCustName());
                    System.out.println("Customer IC: " + customer.getCustIC());
                    double totalAmountPaid = 0;
                    for (ItemInformation purchasedItem : customer.getPurchasedItems()) {
                        System.out.println("Item ID: " + purchasedItem.getItemID());
                        System.out.println("Item Name: " + purchasedItem.getItemName());
                        totalAmountPaid += purchasedItem.getItemPrice();
                    }
                    System.out.println("Total Amount Paid: " + totalAmountPaid);
                    System.out.println("=========================================================");
                }
            }
        } while (menu != 2); // close while
    }
}
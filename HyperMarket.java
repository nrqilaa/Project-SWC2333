import java.io.File;
import java.io.*;
import java.util.*;
import java.util.Queue;

public class HyperMarket {
    public static void main(String[] args) {
        Queue<CustomerInformation> counter1Queue = new LinkedList<>();
        Queue<CustomerInformation> counter2Queue = new LinkedList<>();
        Queue<CustomerInformation> counter3Queue = new LinkedList<>();
        Stack<CustomerInformation> completeStack = new Stack<>();

        List<CustomerInformation> customerList = readCustomerInformationFromFile("CustomerList.txt");

        //Store customers in the appropriate queues based on the number of items purchased
        int counter1Count = 0;
        int counter2Count = 0;
        int totalCustomersProcessed = 0;

        for (CustomerInformation customer : customerList) {
            int itemCount = customer.getPurchasedItems().size();
            if (itemCount <= 5) {
                if (counter1Count < 5) {
                    customer.setCounterPaid(1);
                    counter1Queue.offer(customer);
                    counter1Count++;
                } else if (counter2Count < 5) {
                    customer.setCounterPaid(2);
                    counter2Queue.offer(customer);
                    counter2Count++;
                } else {
                    // Reset counters when both counters 1 and 2 have 5 customers
                    counter1Count = 1;
                    counter2Count = 0;
                    customer.setCounterPaid(1);
                    counter1Queue.offer(customer);
                }
            } else {
                customer.setCounterPaid(3);
                counter3Queue.offer(customer);
            }
            totalCustomersProcessed++;
        }

        //Process payments and store completed customers in completeStack
        int counter = 1;
        while (!counter1Queue.isEmpty() || !counter2Queue.isEmpty() || !counter3Queue.isEmpty()) {
            switch (counter) {
                case 1:
                    if (counter1Queue.isEmpty() && totalCustomersProcessed < customerList.size()) {
                        counter++;
                        break;
                    }
                    processQueue(counter1Queue, completeStack);
                    counter++;
                    break;
                case 2:
                    if (counter2Queue.isEmpty() && totalCustomersProcessed < customerList.size()) {
                        counter++;
                        break;
                    }
                    processQueue(counter2Queue, completeStack);
                    counter++;
                    break;
                case 3:
                    processQueue(counter3Queue, completeStack);
                    counter = 1;
                    break;
            }
        }

        // Display completed customers and amount purchased
        displayCompletedCustomers(completeStack);
    }

    private static void processQueue(Queue<CustomerInformation> queue, Stack<CustomerInformation> completeStack) {
        int customerCount = Math.min(queue.size(), 5);
        for (int i = 0; i < customerCount; i++) {
            CustomerInformation customer = queue.poll();
            completeStack.push(customer);
            displayCustomerDetails(customer);
        }
    }

    private static void displayCompletedCustomers(Stack<CustomerInformation> completeStack) {
        while (!completeStack.isEmpty()) {
            CustomerInformation customer = completeStack.pop();
            displayCustomerDetails(customer);
        }
    }

    private static void displayCustomerDetails(CustomerInformation customer) {
        System.out.println("-----------------");
        System.out.println("Customer Name: " + customer.getCustName());
        System.out.println("IC: " + customer.getCustIC());
        System.out.println("Counter Paid: " + customer.getCounterPaid());
        System.out.println("Items :");
        for (ItemInformation purchasedItem : customer.getPurchasedItems()) {
            System.out.println("Item Name: " + purchasedItem.getItemName());
            System.out.println("Item ID: " + purchasedItem.getItemID());
            System.out.println("Item Price: " + purchasedItem.getItemPrice());
            System.out.println("Date of Purchase: " + purchasedItem.getDatePurchase());
            System.out.println("====================================");
        }
    }

    private static List<CustomerInformation> readCustomerInformationFromFile(String filename) {
        List<CustomerInformation> customerList = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                String custId = parts[0];
                String custIC = parts[1];
                int counterPaid = Integer.parseInt(parts[2]);

                CustomerInformation customer = new CustomerInformation(custId, custIC, counterPaid);

                // Process items purchased
                for (int i = 3; i < parts.length; i += 4) {
                    String itemID = parts[i];
                    String itemName = parts[i + 1];
                    double itemPrice = Double.parseDouble(parts[i + 2]);
                    String datePurchase = parts[i + 3];

                    ItemInformation item = new ItemInformation(itemID, itemName, itemPrice, datePurchase);
                    customer.addItem(item);
                }

                customerList.add(customer);
            }
            
            //close file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return customerList;
    }
}
import java.util.*;
import java.io.*;
import java.util.Queue;

public class HypermarketSelfCheckoutSystem {
    private List<CustomerInformation> customerList;
    private Queue<CustomerInformation> counter1Queue;
    private Queue<CustomerInformation> counter2Queue;
    private Queue<CustomerInformation> counter3Queue;
    private Stack<CustomerInformation> completeStack;

    public HypermarketSelfCheckoutSystem() {
        customerList = new ArrayList<>();
        counter1Queue = new LinkedList<>();
        counter2Queue = new LinkedList<>();
        counter3Queue = new LinkedList<>();
        completeStack = new Stack<>();
    }

    public static void main(String[] args) {
        HypermarketSelfCheckoutSystem system = new HypermarketSelfCheckoutSystem();
        try {
            system.readCustomerDataFromFile("CustomerList.txt");
            system.processCustomers();
        } catch (IOException e) {
            System.out.println("Error reading customer data: " + e.getMessage());
        }
    }

    public void readCustomerDataFromFile(String fileName) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            CustomerInformation cust;
            ItemInformation item;
            String inData;

            while ((inData = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ";");
                String custName = st.nextToken();
                String custIC = st.nextToken();
                int counterPaid = Integer.parseInt(st.nextToken());
                String itemID = st.nextToken();
                String itemName = st.nextToken();
                double itemPrice = Double.parseDouble(st.nextToken());
                String datePurchase = st.nextToken();

                cust = new CustomerInformation(custName, custIC, counterPaid);
                item = new ItemInformation(itemID, itemName, itemPrice, datePurchase);
                cust.addItem(item);
                customerList.add(cust);
            }
        } catch (FileNotFoundException fnfe) {
            throw new IOException("File was not found!", fnfe);
        }
    }

    public void processCustomers() {
        int counter1CustomerCount = 0;
        int counter2CustomerCount = 0;

        for (CustomerInformation customer : customerList) {
            if (customer.getPurchasedItems().size() <= 5) {
                if (counter1CustomerCount < 5) {
                    counter1Queue.offer(customer);
                    counter1CustomerCount++;
                } else {
                    counter2Queue.offer(customer);
                    counter2CustomerCount++;
                }
            } else {
                counter3Queue.offer(customer);
            }
        }

        int counter1PaymentCount = 0;
        int counter2PaymentCount = 0;

        while (!counter1Queue.isEmpty() || !counter2Queue.isEmpty() || !counter3Queue.isEmpty()) {
            if (!counter1Queue.isEmpty()) {
                CustomerInformation customer = counter1Queue.poll();
                processPayment(customer);
                counter1PaymentCount++;
                if (counter1PaymentCount == 5 && !counter2Queue.isEmpty()) {
                    counter1PaymentCount = 0;
                    counter1Queue.offer(counter2Queue.poll());
                }
            }

            if (!counter2Queue.isEmpty()) {
                CustomerInformation customer = counter2Queue.poll();
                processPayment(customer);
                counter2PaymentCount++;
                if (counter2PaymentCount == 5 && !counter1Queue.isEmpty()) {
                    counter2PaymentCount = 0;
                    counter2Queue.offer(counter1Queue.poll());
                }
            }

            if (!counter3Queue.isEmpty()) {
                CustomerInformation customer = counter3Queue.poll();
                processPayment(customer);
            }
        }
    }

    private void processPayment(CustomerInformation customer) {
        System.out.println("Counter 1:");
        while (!counter1Queue.isEmpty()) {
            customer = counter1Queue.poll();
            completeStack.push(customer);
            System.out.println("Customer Name: " + customer.getCustName());
            System.out.println("Customer IC: " + customer.getCustIC());
            System.out.println("Counter Paid: " + customer.getCounterPaid());
            System.out.println("Items Purchased:");
            System.out.println("--------------");
            double totalAmount = 0.0;
            for (ItemInformation item : customer.getPurchasedItems()) {
                System.out.println("Item ID: " + item.getItemID());
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Price: " + item.getItemPrice());
                System.out.println("Date of Purchase: " + item.getDatePurchase());
                totalAmount += item.getItemPrice();
            }
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("===============================");

            completeStack.push(customer);
        }
        
        System.out.println("Counter 2:");
        while (!counter2Queue.isEmpty()) {
            customer = counter2Queue.poll();
            completeStack.push(customer);
            System.out.println("Customer Name: " + customer.getCustName());
            System.out.println("Customer IC: " + customer.getCustIC());
            System.out.println("Counter Paid: " + customer.getCounterPaid());
            System.out.println("Items Purchased:");
            System.out.println("--------------");
            double totalAmount = 0.0;
            for (ItemInformation item : customer.getPurchasedItems()) {
                System.out.println("Item ID: " + item.getItemID());
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Price: " + item.getItemPrice());
                System.out.println("Date of Purchase: " + item.getDatePurchase());
                totalAmount += item.getItemPrice();
            }
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("===============================");

            completeStack.push(customer);
        }

        System.out.println("Counter 3:");
        while (!counter3Queue.isEmpty()) {
            customer = counter3Queue.poll();
            completeStack.push(customer);
            System.out.println("Customer Name: " + customer.getCustName());
            System.out.println("Customer IC: " + customer.getCustIC());
            System.out.println("Counter Paid: " + customer.getCounterPaid());
            System.out.println("Items Purchased:");
            System.out.println("--------------");
            double totalAmount = 0.0;
            for (ItemInformation item : customer.getPurchasedItems()) {
                System.out.println("Item ID: " + item.getItemID());
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Price: " + item.getItemPrice());
                System.out.println("Date of Purchase: " + item.getDatePurchase());
                totalAmount += item.getItemPrice();
            }
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("===============================");

            completeStack.push(customer);
        }
    }
}

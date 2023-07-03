import java.util.*;

public class CustomerInformation {
    private String CustName;
    private String CustIC;
    private int CounterPaid;
    private LinkedList<ItemInformation> PurchasedItems;
    static final double TotalAmountPaid = 0.0;

    // Constructor without parameter
    public CustomerInformation() {
        CustName = "";
        CustIC = "";
        CounterPaid = 0;
        PurchasedItems = new LinkedList<>();
    }

    // Constructor with parameter
    public CustomerInformation(String CustName, String CustIC, int CounterPaid ) {
        this.CustName = CustName;
        this.CustIC = CustIC;
        this.CounterPaid = CounterPaid;
        PurchasedItems = new LinkedList<>();
    }

    // Mutator for all attributes
    public void setAll(String CustName, String CustIC, int CounterPaid) {
        this.CustName = CustName;
        this.CustIC = CustIC;
        this.CounterPaid = CounterPaid;
    }

    // Mutator for each attribute
    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public void setCustIC(String CustIC) {
        this.CustIC = CustIC;
    }

    public void setCounterPaid(int CounterPaid) {
        this.CounterPaid = CounterPaid;
    }
    

    // Accessor for each attribute
    public String getCustName() {
        return CustName;
    }

    public String getCustIC() {
        return CustIC;
    }

    public int getCounterPaid() {
        return CounterPaid;
    }

    public LinkedList<ItemInformation> getPurchasedItems() {
        return PurchasedItems;
    }
    
    // Add item to the purchased items list
    public void addItem(ItemInformation item) {
        PurchasedItems.add(item);
    }
    
    // toString method
    public String toString() {
        return "Customer Name: " + CustName + "\n" +
                "Customer IC: " + CustIC + "\n" +
                "Counter Paid: " + CounterPaid + "\n";
    }
}

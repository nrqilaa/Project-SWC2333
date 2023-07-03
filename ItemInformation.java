public class ItemInformation
{
    private String ItemID;
    private String ItemName;
    private double ItemPrice;
    private String DatePurchase;
    
    //constructor without parameter
    public ItemInformation()
    {
        ItemID = "";
        ItemName = "";
        ItemPrice = 0;
        DatePurchase = "";
    }
    
    //contrustor with parameter
    public ItemInformation(String ItemID, String ItemName, double ItemPrice, String DatePurchase)
    {
        this.ItemID = ItemID;
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.DatePurchase = DatePurchase;
    }
    
    //mutator for all attributes
    public void setAll(String ItemID, String ItemName, double ItemPrice, String DatePurchase)
    {
        this.ItemID = ItemID;
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.DatePurchase = DatePurchase;
    }
    
    //mutator for each attributes
    public void setItemID(String ItemID)
    {
        this.ItemID = ItemID;
    }
    
     public void setItemName(String ItemName)
    {
        this.ItemName = ItemName;
    }
    
      public void setItemPrice(double ItemPrice)
    {
        this.ItemPrice = ItemPrice;
    }
    
      public void setDatePurchase(String DatePurchase)
    {
        this.DatePurchase = DatePurchase;
    }
    
    //accessor for each attribute
    public String getItemID()
    {
        return ItemID;
    }
    
     public String getItemName()
    {
        return ItemName;
    }
    
     public double getItemPrice()
    {
        return ItemPrice;
    }
    
     public String getDatePurchase()
    {
        return DatePurchase;
    }
    
    //to string method
    public String toString()
    {
        return (ItemID + "\n" + ItemName + "\n" + ItemPrice + "\n" + DatePurchase );
    }
}

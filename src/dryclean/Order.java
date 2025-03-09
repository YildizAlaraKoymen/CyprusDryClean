
package dryclean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private boolean paidStatus;
    private List<LaundryItem> itemList;

    public Order()
    {
        this.orderDate = LocalDate.now();
        this.itemList = new ArrayList<LaundryItem>();
    }
    public Order(boolean paidStatus, List<LaundryItem> itemList)
    {
        this.orderDate = LocalDate.now();
        this.paidStatus = paidStatus;
        this.itemList = itemList;
    }

    //Getters
    public LocalDate getOrderDate(){return orderDate;}
    public boolean getPaidStatus(){return paidStatus;}
    public List<LaundryItem> getItemList(){return itemList;}

    //Setters
    public void setOrderDate(LocalDate orderDate){this.orderDate = orderDate;}
    public void setPaidStatus(boolean paidStatus){this.paidStatus = paidStatus;}
    public void setItemList(List<LaundryItem> itemList){this.itemList = itemList;}

    public double totalOrderCost()
    {
        double cost = 0;
        for(LaundryItem item: itemList)
        {
            cost = item.totalCost() + cost;
        }
        return cost;
    }

    public String toString()
    {
        String item_info = " ";
        int count = 0;
        for(LaundryItem item: getItemList())
        {
            item_info = "type: " + item.getType() + "\n" + "quantity: " + item.getQuantity() + "\n" + "price: " + item.getPrice() + "\n"+ "notes: "
                    + item.getNotes() + "\n" + "Assigned Employee " + item.getAssigned().getName() + item.getAssigned().getSurname() + " ID: " + item.getAssigned().getID() + "\n"+ item_info;
        }

        return "paid status: " + getPaidStatus() + "\n" + item_info;
    }
}

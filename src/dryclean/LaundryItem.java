
package dryclean;

public abstract class LaundryItem implements cleaningStyle{
    private String type;
    private int quantity;
    private int price;
    private String notes;
    private Employee assigned;

    protected LaundryItem(){
        this.quantity = 0;
        this.price = 0;
    }
    protected LaundryItem(String type, int quantity, int price, String notes, Employee assigned)
    {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
        this.assigned = assigned;
    }
    protected LaundryItem(LaundryItem item)
    {
        this.type = item.getType();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.notes = item.getNotes();
        this.assigned = item.getAssigned();
    }
    //Getters
    public String getType(){return type;}
    public int getQuantity(){return quantity;}
    public int getPrice(){return price;}
    public String getNotes(){return notes;}
    public Employee getAssigned(){return assigned;}

    //Setters
    public void setType(String type){this.type = type;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public void setPrice(int price){this.price = price;}
    public void setNotes(String notes){this.notes = notes;}
    public void setAssigned(Employee assigned){this.assigned = assigned;}

    public double totalCost()
    {
        return quantity*price;
    }

    public double electricityRate() {return 1.05;}

    public double waterRate() {return 1.01;}

    public double ironingRate() {return 10;}

    public String toString()
    {
        return "type: " + getType() + " quantity: " + getQuantity() + " price: " + getPrice() + " notes: "
                + getNotes() + " assigned Employee ID: " + getAssigned().getID() + " " + getAssigned().getSurname();
    }

}

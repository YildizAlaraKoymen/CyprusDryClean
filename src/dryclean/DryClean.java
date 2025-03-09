
package dryclean;

public class DryClean extends LaundryItem{

    protected DryClean(){}
    protected DryClean(String type, int quantity, int price, String notes, Employee assigned)
    {
        super(type, quantity, price, notes, assigned);
    }
    protected DryClean(DryClean dryclean)
    {
        super(dryclean);
    }
    public double TotalCost()
    {
        return getQuantity()*getPrice()*electricityRate();
    }

}

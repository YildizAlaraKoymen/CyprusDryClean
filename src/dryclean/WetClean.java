
package dryclean;

public class WetClean extends LaundryItem {

    protected WetClean(){}
    protected WetClean(String type, int quantity, int price, String notes, Employee assigned)
    {
        super(type, quantity, price, notes, assigned);
    }
    protected WetClean(WetClean wetclean)
    {
        super(wetclean);
    }
    public double totalCost()
    {
        return getQuantity()*getPrice()*waterRate() + ironingRate()*getQuantity();
    }

}

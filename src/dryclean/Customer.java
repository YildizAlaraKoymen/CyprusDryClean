
package dryclean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.List;

public abstract class Customer extends User implements customerManagement, Comparable{
    private LocalDate registrationDate;
    private List<Order> orders;

    protected Customer()
    {
        this.registrationDate = LocalDate.now();
        this.orders = new ArrayList<Order>();
    }
    protected Customer(int ID, LocalDate dateOfBirth, String name, String surname, LocalDate registrationDate)
    {
        super(ID, dateOfBirth, name, surname);
        this.registrationDate = registrationDate;
        this.orders = new ArrayList<Order>();
    }
    protected Customer(Customer customer)
    {
        super(customer);
        this.registrationDate = customer.getRegistrationDate();
        this.orders = customer.orders;

    }

    //Getters
    public LocalDate getRegistrationDate(){return registrationDate;}
    public List<Order> getOrders(){return orders;}
    public double getMinDeposit(){return minDeposit;}

    //Setters
    public void setRegistrationDate(LocalDate registrationDate){this.registrationDate = registrationDate;}
    public void setOrders(List<Order> orders){this.orders = orders;}

    public void putOrder(Order order)
    {
        this.orders.add(order);
    }

    public double calculateDiscount()
    {
        if(this instanceof Subscribed)
        {
            return (double) 10/100;
        }
        if(this instanceof UnSubscribed)
        {
            if((LocalDate.now().getYear()- this.getRegistrationDate().getYear())>= 10)
                return (double) 5/100;
        }
        return 0;
    }

    public double calculateTotalOrderIncome()
    {
        double income = 0;
        for(Order order: orders)
        {
            income = order.totalOrderCost() + income;
        }
        return income;
    }

    public int compareTo(Customer customer) {
        double income1 = customer.calculateTotalOrderIncome();
        double income2 = customer.calculateTotalOrderIncome();

        return Double.compare(income1, income2);
    }

    @Override
    public String toString()
    {
        return "Customer " + getID() + ":\nFull name: " + getName() + " " + getSurname() + "\nDate of Birth: " +
                getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nDate of registration: " +
                this.registrationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nTotal number of orders: "
                + this.orders.size();
    }

}

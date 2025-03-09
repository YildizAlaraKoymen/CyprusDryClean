
package dryclean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Subscribed extends Customer{
    private LocalDate subscriptionDate;
    private double depositPaid;

    protected Subscribed()
    {
        this.subscriptionDate = LocalDate.now();
        this.depositPaid = minDeposit;
    }
    protected Subscribed(int ID)
    {
        setID(ID);
    }
    protected Subscribed(int ID, LocalDate dateOfBirth, String name, String surname, LocalDate registrationDate, LocalDate subscriptionDate, double depositPaid)
    {
        super(ID, dateOfBirth, name, surname, registrationDate);
        this.subscriptionDate = subscriptionDate;
        this.depositPaid = depositPaid;
    }
    protected Subscribed(Subscribed subscribed)
    {
        super(subscribed);
        this.subscriptionDate = getSubscriptionDate();
        this.depositPaid = getDepositPaid();
    }

    //Getters
    public LocalDate getSubscriptionDate(){return subscriptionDate;}
    public double getDepositPaid(){return depositPaid;}

    //Setters
    public void setSubscriptionDate(LocalDate subscriptionDate){this.subscriptionDate = subscriptionDate;}
    public void setDepositPaid(double depositPaid){this.depositPaid = depositPaid;}

    @Override
    public String toString()
    {
        return "Customer " + getID() + ":\nFull name: " + getName() + " " + getSurname() + "\nDate of Birth: " +
                getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nDate of registration: " +
                this.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nTotal number of orders: "
                + this.getOrders().size() + "\nDate of Subscription: " + subscriptionDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + "\nDeposit paid: " + depositPaid;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

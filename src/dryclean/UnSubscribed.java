
package dryclean;

import java.time.LocalDate;
import java.util.List;

public class UnSubscribed extends Customer {

    protected UnSubscribed(){}
    protected UnSubscribed(int ID)
    {
        setID(ID);
    }
    protected UnSubscribed(int ID, LocalDate dateOfBirth, String name, String surname, LocalDate registrationDate)
    {
        super(ID, dateOfBirth, name, surname, registrationDate);
    }
    protected UnSubscribed(UnSubscribed unSubscribed)
    {
        super(unSubscribed);
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

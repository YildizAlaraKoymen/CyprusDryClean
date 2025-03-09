
package dryclean;
import java.time.LocalDate;

public abstract class User {

    private int ID;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;

    protected User() {}

    protected User(int ID, LocalDate dateOfBirth, String name, String surname)
    {
        this.ID = ID;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
    }
    protected User(User user)
    {
        this.ID = user.getID();
        this.dateOfBirth = user.getDateOfBirth();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

    //Getters
    public int getID(){return ID;}
    public LocalDate getDateOfBirth(){return dateOfBirth;}
    public String getName(){return name;}
    public String getSurname(){return surname;}

    //Setters
    public void setID(int ID){this.ID = ID;}
    public void setDateOfBirth(LocalDate dateOfBirth){this.dateOfBirth = dateOfBirth;}
    public void setName(String name){this.name = name;}
    public void setSurname(String surname){this.surname = surname;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() == o.getClass())
        {
            User user = (User) o;
            return ID == user.ID;
        }
        else
            return false;
    }
}

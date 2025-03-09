
package dryclean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Employee extends User {
    private LocalDate startDate;
    private String nationality;
    private HashMap<LocalDate, String> healthInspection;
    private LocalDate workPermitEndDate;

    protected Employee()
    {
        this.healthInspection = new HashMap<LocalDate, String>();
    }
    protected Employee(int ID)
    {
        setID(ID);
    }
    protected Employee(int ID, LocalDate dateOfBirth, String name, String surname, LocalDate startDate, String nationality, HashMap<LocalDate, String> healthInspection, LocalDate workPermitEndDate)
    {
        setID(ID);
        setDateOfBirth(dateOfBirth);
        setName(name);
        setSurname(surname);
        this.startDate = startDate;
        this.nationality = nationality;
        this.healthInspection = healthInspection;
        this.workPermitEndDate = workPermitEndDate;
    }
    protected Employee(Employee employee)
    {
        if(employee == null)
            System.out.println("Can't copy from a null object");
        else
        {
            setID(employee.getID());
            setDateOfBirth(employee.getDateOfBirth());
            setName(employee.getName());
            setSurname(employee.getSurname());
            this.startDate = employee.getStartDate();
            this.nationality = employee.getNationality();
            this.healthInspection = employee.getHealthInspection();
            this.workPermitEndDate = employee.getWorkPermitEndDate();
        }
    }

    //Getters

    public LocalDate getStartDate(){return startDate;}
    public String getNationality(){return nationality;}
    public HashMap<LocalDate, String> getHealthInspection(){return healthInspection;}
    public LocalDate getWorkPermitEndDate(){return workPermitEndDate;}
    //Setters
    public void setStartDate(LocalDate startDate){this.startDate = startDate;}
    public void setNationality(String nationality){this.nationality = nationality;}
    public void setHealthInspection(HashMap healthInspection){this.healthInspection = healthInspection;}
    public void setWorkPermitEndDate(LocalDate workPermitEndDate){this.workPermitEndDate = workPermitEndDate;}

    @Override
    public String toString() {
        return "Employee " + getID() + ":\nFull name: " + getName()
                + " " + getSurname() + "\nDate of birth: " + getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\nStart date: "
                + this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

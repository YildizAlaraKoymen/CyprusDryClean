package dryclean;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class LoadInfo {

    public void createEmployee(String fileName, CyprusDryClean data) {

        try {
            DataOutputStream file = new DataOutputStream(new FileOutputStream(fileName));
            //ID;dateOfBirth;name;surname;startDate;nationality;healthInspection;workPermitEndDate
            for (Employee empData : data.empList) {
                String line = empData.getID() + ";" + empData.getDateOfBirth() + ";" + empData.getName() + ";" +
                        empData.getSurname() + ";" + empData.getStartDate() + ";" + empData.getNationality() + ";" +
                        empData.getHealthInspection() + ";" + empData.getWorkPermitEndDate() + ";";
                file.writeUTF(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public HashMap<LocalDate, String> hashMapParser(String str) {

        str = str.substring(1, str.length() - 1);

        // Split by ", " to get key-value pairs
        String[] entries = str.split(", ");

        // Create a new HashMap to store the parsed values
        HashMap<LocalDate, String> map = new HashMap<>();

        // Iterate over each key-value pair
        for (String entry : entries) {
            // Split by "=" to separate the key and value
            String[] keyValue = entry.split("=", 2); // Limit to 2 to handle values with '='
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                LocalDate dateKey = LocalDate.parse(key, DateTimeFormatter.ISO_LOCAL_DATE);
                map.put(dateKey, value);
            }
        }
        return map;
    }


    public void createSubscribed(String fileName, CyprusDryClean data){

        try{
            DataOutputStream file = new DataOutputStream(new FileOutputStream(fileName));
            //ID;dateOfBirth;name;surname;registrationDate;subscriptionDate;depositPaid
            for (Customer customerData : data.customerList) {
                if(customerData instanceof Subscribed subData)
                {
                    String line = subData.getID() + ";" + subData.getDateOfBirth() + ";" + subData.getName() + ";" +
                            subData.getSurname() + ";" + subData.getRegistrationDate() + ";" +
                            subData.getSubscriptionDate() + ";" + subData.getDepositPaid() + ";";
                    file.writeUTF(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void createUnSubscribed(String fileName, CyprusDryClean data){

        try{
            DataOutputStream file = new DataOutputStream(new FileOutputStream(fileName));
            //ID;dateOfBirth;name;surname;registrationDate
            for (Customer customerData : data.customerList) {
                if(customerData instanceof UnSubscribed unsubData)
                {
                    String line = unsubData.getID() + ";" + unsubData.getDateOfBirth() + ";" + unsubData.getName() + ";" +
                            unsubData.getSurname() + ";" + unsubData.getRegistrationDate() + ";";
                    file.writeUTF(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void readEmployee(String fileName, CyprusDryClean c)
    {
        try{
            DataInputStream file = new DataInputStream(new FileInputStream(fileName));
            while(true)
            {
                try{
                    //ID;dateOfBirth;name;surname;startDate;nationality;healthInspection;workPermitEndDate
                    String line = file.readUTF();
                    String[] records = line.split(";");
                    int ID = Integer.parseInt(records[0]);
                    LocalDate dateOfBirth = LocalDate.parse(records[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    String name = records[2];
                    String surname = records[3];
                    LocalDate startDate = LocalDate.parse(records[4], DateTimeFormatter.ISO_LOCAL_DATE);
                    String nationality = records[5];
                    HashMap<LocalDate, String> healthInspection = hashMapParser(records[6]);
                    LocalDate workPermitEndDate = LocalDate.parse(records[7], DateTimeFormatter.ISO_LOCAL_DATE);
                    c.empList.add(new Employee(ID, dateOfBirth, name, surname, startDate, nationality, healthInspection, workPermitEndDate));

                }catch (EOFException e){
                    break;
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void readCustomer(String fileName, CyprusDryClean c, Customer type)
    {
        try{
            DataInputStream file = new DataInputStream(new FileInputStream(fileName));
            while(true)
            {
                try{
                    //ID;dateOfBirth;name;surname;registrationDate;subscriptionDate;depositPaid
                    String line = file.readUTF();
                    String[] records = line.split(";");
                    int ID = Integer.parseInt(records[0]);
                    LocalDate dateOfBirth = LocalDate.parse(records[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    String name = records[2];
                    String surname = records[3];
                    LocalDate registrationDate = LocalDate.parse(records[4], DateTimeFormatter.ISO_LOCAL_DATE);
                    Customer newCustomer;
                    if(type instanceof Subscribed)
                    {
                        LocalDate subscriptionDate = LocalDate.parse(records[5], DateTimeFormatter.ISO_LOCAL_DATE);
                        double depositPaid = Double.parseDouble(records[6]);
                        newCustomer = new Subscribed(ID, dateOfBirth, name, surname, registrationDate, subscriptionDate, depositPaid);
                    }
                    else
                    {
                        newCustomer = new UnSubscribed(ID, dateOfBirth, name, surname, registrationDate);
                    }

                    c.customerList.add(newCustomer);

                }catch (EOFException e){
                    break;
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public CyprusDryClean readData()
    {
        //This is data from populated class created for the previous assignment NOT already loaded cyprusDryClean data!
        CyprusDryClean data = (new PopulateData()).populated;
        createEmployee("employee.dat", data);
        createSubscribed("subscribed.dat", data);
        createUnSubscribed("unsubscribed.dat", data);

        CyprusDryClean c = new CyprusDryClean();

        readEmployee("employee.dat", c);
        readCustomer("subscribed.dat", c, new Subscribed());
        readCustomer("unsubscribed.dat", c, new UnSubscribed());

        return c;
    }
}


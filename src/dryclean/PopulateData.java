
package dryclean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PopulateData {

    CyprusDryClean populated;

    PopulateData()
    {
        populated = new CyprusDryClean();

        List<Employee> employee_list = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setName("Arnold");
        employee1.setSurname("Schwarzenegger");
        employee1.setNationality("Austria");
        employee1.setID(100);
        employee1.setStartDate(LocalDate.parse("08/04/2021", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee1.setWorkPermitEndDate(LocalDate.parse("09/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee1.setDateOfBirth(LocalDate.parse("30/11/1947", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee1.getHealthInspection().put(LocalDate.parse("01/02/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")), "No health issues observed");
        employee1.getHealthInspection().put(LocalDate.parse("10/04/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")), "Covid detected");

        Employee employee2 = new Employee();
        employee2.setName("Michael");
        employee2.setSurname("Jackson");
        employee2.setNationality("United States of America");
        employee2.setID(101);
        employee2.setStartDate(LocalDate.parse("12/10/2023", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee2.setWorkPermitEndDate(LocalDate.parse("09/12/2025", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee2.setDateOfBirth(LocalDate.parse("29/08/1958", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee2.getHealthInspection().put(LocalDate.parse("25/06/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")), "Ear ache");

        Employee employee3 = new Employee();
        employee3.setName("Tarkan");
        employee3.setSurname("Tevetoğlu");
        employee3.setNationality("Turkey");
        employee3.setID(102);
        employee3.setStartDate(LocalDate.parse("05/08/2023", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee3.setWorkPermitEndDate(LocalDate.parse("05/12/2025", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee3.setDateOfBirth(LocalDate.parse("17/10/1972", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        employee3.getHealthInspection().put(LocalDate.parse("21/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")), "Throat pain");
        employee3.getHealthInspection().put(LocalDate.parse("23/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")), "No health issues observed");

        employee_list.add(employee1);
        employee_list.add(employee2);
        employee_list.add(employee3);

        List<Customer> customer_list = new ArrayList<>();

        Subscribed customer1 = new Subscribed();
        customer1.setID(110);
        customer1.setDateOfBirth(LocalDate.parse("25/05/1976", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer1.setRegistrationDate(LocalDate.parse("10/05/2012", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer1.setName("Cillian");
        customer1.setSurname("Murphy");
        customer1.setSubscriptionDate(LocalDate.parse("22/11/2022", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer1.setDepositPaid(25);

        List<Order> order_list1 = new ArrayList<>();
        Order order1 = new Order();
        order1.setPaidStatus(true);
        order1.setOrderDate(LocalDate.parse("23/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));

        List<LaundryItem> item_list1 = new ArrayList<>();
        LaundryItem new_dry_clean1 = new DryClean();
        new_dry_clean1.setPrice(30);
        new_dry_clean1.setAssigned(employee1);
        new_dry_clean1.setNotes("stained");
        new_dry_clean1.setType("jeans");
        new_dry_clean1.setQuantity(2);
        item_list1.add(new_dry_clean1);

        LaundryItem new_wet_clean1 = new WetClean();
        new_wet_clean1.setPrice(20);
        new_wet_clean1.setAssigned(employee2);
        new_wet_clean1.setNotes("Tear on right shoulder");
        new_wet_clean1.setType("blouse");
        new_wet_clean1.setQuantity(1);
        item_list1.add(new_wet_clean1);

        order1.setItemList(item_list1);
        order_list1.add(order1);

        customer1.setOrders(order_list1);
        customer_list.add(customer1);

        Subscribed customer2 = new Subscribed();
        customer2.setID(111);
        customer2.setDateOfBirth(LocalDate.parse("01/03/1994", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer2.setRegistrationDate(LocalDate.parse("06/02/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer2.setName("Justin");
        customer2.setSurname("Bieber");
        customer2.setSubscriptionDate(LocalDate.parse("06/02/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer2.setDepositPaid(60);

        List<Order> order_list2 = new ArrayList<>();
        Order order2 = new Order();
        order2.setPaidStatus(false);
        order2.setOrderDate(LocalDate.parse("20/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));

        List<LaundryItem> item_list2 = new ArrayList<>();
        LaundryItem new_dry_clean2 = new DryClean();
        new_dry_clean2.setPrice(15);
        new_dry_clean2.setAssigned(employee1);
        new_dry_clean2.setNotes("stained");
        new_dry_clean2.setType("shirt");
        new_dry_clean2.setQuantity(1);
        item_list2.add(new_dry_clean2);

        LaundryItem new_wet_clean2 = new WetClean();
        new_wet_clean2.setPrice(100);
        new_wet_clean2.setAssigned(employee1);
        new_wet_clean2.setNotes("Coffee stain");
        new_wet_clean2.setType("Coat");
        new_wet_clean2.setQuantity(2);
        item_list2.add(new_wet_clean2);

        order2.setItemList(item_list2);
        order_list2.add(order2);

        customer2.setOrders(order_list2);
        customer_list.add(customer2);

        UnSubscribed customer3 = new UnSubscribed();
        customer3.setID(112);
        customer3.setDateOfBirth(LocalDate.parse("04/08/1981", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer3.setRegistrationDate(LocalDate.parse("17/05/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer3.setName("Meghan");
        customer3.setSurname("Markle");

        List<Order> order_list3 = new ArrayList<>();
        Order order3 = new Order();
        order3.setPaidStatus(false);
        order3.setOrderDate(LocalDate.parse("20/11/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));

        List<LaundryItem> item_list3 = new ArrayList<>();
        LaundryItem new_dry_clean3 = new DryClean();
        new_dry_clean3.setPrice(200);
        new_dry_clean3.setAssigned(employee2);
        new_dry_clean3.setNotes("frayed");
        new_dry_clean3.setType("skirt");
        new_dry_clean3.setQuantity(3);
        item_list3.add(new_dry_clean3);

        LaundryItem new_wet_clean3 = new WetClean();
        new_wet_clean3.setPrice(80);
        new_wet_clean3.setAssigned(employee3);
        new_wet_clean3.setNotes("dirt stain");
        new_wet_clean3.setType("Shoes");
        new_wet_clean3.setQuantity(2);
        item_list3.add(new_wet_clean3);

        order3.setItemList(item_list3);
        order_list3.add(order3);

        customer3.setOrders(order_list3);
        customer_list.add(customer3);

        UnSubscribed customer4 = new UnSubscribed();
        customer4.setID(113);
        customer4.setDateOfBirth(LocalDate.parse("31/01/1981", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer4.setRegistrationDate(LocalDate.parse("17/11/2023", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));
        customer4.setName("Justin");
        customer4.setSurname("Timberlake");

        List<Order> order_list4 = new ArrayList<>();
        Order order4 = new Order();
        order4.setPaidStatus(false);
        order4.setOrderDate(LocalDate.parse("28/10/2024", DateTimeFormatter.ofPattern("[dd/MM/yyyy]")));

        List<LaundryItem> item_list4 = new ArrayList<>();
        LaundryItem new_dry_clean4 = new DryClean();
        new_dry_clean4.setPrice(20);
        new_dry_clean4.setAssigned(employee1);
        new_dry_clean4.setNotes("ripped");
        new_dry_clean4.setType("jeans");
        new_dry_clean4.setQuantity(1);
        item_list4.add(new_dry_clean4);

        LaundryItem new_wet_clean4 = new WetClean();
        new_wet_clean4.setPrice(30);
        new_wet_clean4.setAssigned(employee3);
        new_wet_clean4.setNotes("1");
        new_wet_clean4.setType("Button up shirt");
        new_wet_clean4.setQuantity(1);
        item_list4.add(new_wet_clean4);

        order4.setItemList(item_list4);
        order_list4.add(order4);

        customer4.setOrders(order_list4);
        customer_list.add(customer4);

        populated.setEmpList(employee_list);
        populated.setCustomerList(customer_list);

    }
}

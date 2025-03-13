package dryclean;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    Menu(){}
    Menu(JFrame frame)
    {
        setLayout(new GridBagLayout());

        // Menu panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(15, 1, 5, 5)); // 15 rows for each menu item

        // Menu options
        String[] menuItems = {
                "1. Add Employee",
                "2. Delete Employee",
                "3. List Employee Details",
                "4. Add Customer",
                "5. Delete Customer",
                "6. List Customer Details",
                "7. Put Order",
                "8. Show Customer Order Details",
                "9. Show Customer Order Total Cost",
                "10. List Employees",
                "11. List Customers",
                "12. Add Health Inspection",
                "13. Compare Customer Loyalty",
                "14. Print Employee Health Inspection",
                "0. Exit"
        };

        //Row for each menu item
        int i = 0;
        for (String menuItem : menuItems)
        {
            GridBagConstraints gbc = makeGbc(0, i);
            JLabel menuLabel = new JLabel(menuItem);
            add(menuLabel, gbc);
            gbc = makeGbc(1, i);
            JButton button = new JButton("Select");
            add(button, gbc);
            button.addActionListener(e -> handleMenu(menuItem));
            i++;
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    protected void createMenuUI(){
        JFrame frame = new JFrame("Menu");
        frame.getContentPane().add(new Menu(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private GridBagConstraints makeGbc(int x, int y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = x;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = (x == 0) ? GridBagConstraints.LINE_START: GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
    public void handleMenu(String menuItem)
    {
        switch (menuItem) {
            case "1. Add Employee":
                AddEmployee addEmployeePanel = new AddEmployee();
                addEmployeePanel.createAddEmployeeUI();
                break;
            case "2. Delete Employee":
                //deleteEmployee();
                break;
            case "3. List Employee Details":
                //listEmployeeDetails();
                break;
            case "4. Add Customer":
                //addCustomer();
                break;
            case "5. Delete Customer":
                //deleteCustomer();
                break;
            case "6. List Customer Details":
                //getCustomerDetails();
                break;
            case "7. Put Order":
                //putOrder();
                break;
            case "8. Show Customer Order Details":
                //getCustomerOrderDetails();
                break;
            case "9. Show Customer Order Total Cost":
                //getCustomerOrderTotalCost();
                break;
            case "10. List Employees":
                //listEmployees();
                break;
            case "11. List Customers":
                //listCustomers();
                break;
            case "12. Add Health Inspection":
                //addHealthInspection();
                break;
            case "13. Compare Customer Loyalty":
                //compareCustomerLoyalty();
                break;
            case "14. Print Employee Health Inspection":
                //printHealthInspection();
                break;
            case "0. Exit":
                //exit();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option selected.");
        }
    }
}

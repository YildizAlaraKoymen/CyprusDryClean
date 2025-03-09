
package dryclean;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * CyprusDryClean application for managing employees, customers, and orders in a dry cleaning business.
 */

public class CyprusDryClean {
    public List<Employee> empList;
    public List<Customer> customerList;
    /**
     * Default constructor initializing employee and customer lists.
     */

    public CyprusDryClean()
    {
        this.empList = new ArrayList<Employee>();
        this.customerList = new ArrayList<Customer>();
    }

    public static void main(String[] args)
    {
        CyprusDryClean c = (new LoadInfo()).readData();
        System.out.println("Data loaded to files...");
        //c.menu();
        c.start();
    }
    //Getters and setters
    public void setEmpList(List<Employee> empList) {this.empList = empList;}
    public void setCustomerList(List<Customer> customerList){this.customerList = customerList;}
    public List<Employee> getEmpList(){return empList;}
    public List<Customer> getCustomerList(){return customerList;}
    /**
     * Displays the main menu.
     */
    public void start()
    {
        //Main frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 370, 200);
        //frame.setLayout(new BorderLayout());

        //Title
        JLabel titleLabel = new JLabel("Welcome to CyprusDry Clean!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        //Login and register button container
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 50, 50, 50));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        JPanel loginPanel = new JPanel();
        JPanel registerPanel = new JPanel();
        JPanel cancelPanel = new JPanel();

        loginButton.setPreferredSize(new Dimension(100, 50));
        registerButton.setPreferredSize(new Dimension(100, 50));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        loginPanel.add(loginButton);
        registerPanel.add(registerButton);
        cancelPanel.setLayout(new BorderLayout());
        cancelPanel.add(cancelButton, BorderLayout.EAST);
        cancelPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        loginButton.addActionListener(e -> handleLogin("login"));
        registerButton.addActionListener(e -> handleLogin("register"));
        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(loginPanel);
        buttonPanel.add(registerPanel);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(cancelPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        frame.setResizable(false);

    }
    public void handleLogin(String option)
    {
        switch (option){
            case "login":
                login();
                break;
            case "register":
                register();
                break;
        }
    }
    public void login()
    {

    }
    public void register()
    {
        FormLayout register = new FormLayout();
        register.createRegisterUI();
    }
    public void menu()
    {
        // Main frame
        JFrame frame = new JFrame("CyprusDry Clean Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Welcome to CyprusDry Clean!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

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
        for (String menuItem : menuItems) {
            JPanel rowPanel = new JPanel(new BorderLayout(5, 5)); // Panel for each row
            JLabel label = new JLabel(menuItem);
            JButton button = new JButton("Select");

            button.addActionListener(e -> handleMenu(menuItem));

            rowPanel.add(label, BorderLayout.CENTER);
            rowPanel.add(button, BorderLayout.EAST);

            buttonPanel.add(rowPanel);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    /**
     * Handles menu item selection.
     * @param menuItem the menu item selected
     */
    public void handleMenu(String menuItem)
    {
        switch (menuItem) {
            case "1. Add Employee":
                addEmployee();
                break;
            case "2. Delete Employee":
                deleteEmployee();
                break;
            case "3. List Employee Details":
                listEmployeeDetails();
                break;
            case "4. Add Customer":
                addCustomer();
                break;
            case "5. Delete Customer":
                deleteCustomer();
                break;
            case "6. List Customer Details":
                getCustomerDetails();
                break;
            case "7. Put Order":
                putOrder();
                break;
            case "8. Show Customer Order Details":
                getCustomerOrderDetails();
                break;
            case "9. Show Customer Order Total Cost":
                getCustomerOrderTotalCost();
                break;
            case "10. List Employees":
                listEmployees();
                break;
            case "11. List Customers":
                listCustomers();
                break;
            case "12. Add Health Inspection":
                addHealthInspection();
                break;
            case "13. Compare Customer Loyalty":
                compareCustomerLoyalty();
                break;
            case "14. Print Employee Health Inspection":
                printHealthInspection();
                break;
            case "0. Exit":
                exit();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option selected.");
        }
    }

    // Employee Management Methods

    /**
     * Opens a form to add a new employee.
     */
    public void addEmployee()
    {
        // Create the frame and components
        JFrame frame = createFormFrame("Add Employee");
        JPanel panel = new JPanel();

        // Create the input fields
        JTextField idField = createTextField();
        JTextField fullNameField = createTextField();
        JSpinner dobSpinner = createDateSpinner();
        JSpinner startDateSpinner = createDateSpinner();

        // Add components to the frame
        addFormRow(panel, "Employee ID:", idField);
        addFormRow(panel, "Full Name:", fullNameField);
        addFormRow(panel, "Date of Birth:", dobSpinner);
        addFormRow(panel, "Start Date:", startDateSpinner);

        // Add buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleAddEmployeeForm(frame, idField, fullNameField, dobSpinner, startDateSpinner));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Create general form
     * @param label label for frame
     * @return frame
     */

    private JFrame createFormFrame(String label)
    {
        JFrame frame = new JFrame(label);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        return frame;
    }

    /**
     * CreatesTextField
     * @return new JTextField
     */

    private JTextField createTextField()
    {
        return new JTextField();
    }

    /**
     * CreatesPasswordField
     * @return new JPasswordField
     */

    private JPasswordField createPasswordField(){return new JPasswordField();}

    /**
     * InitializeDateSpinner
     * @return JSpinner
     */

    private JSpinner createDateSpinner()
    {
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        return dateSpinner;
    }

    /**
     * Adds row for form
     * @param frame frame for form
     * @param labelText label of form
     * @param component component
     */

    private void addFormRow(JPanel panel, String labelText, JComponent component)
    {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel labelPanel = new JPanel();
        labelPanel.add(label, BorderLayout.EAST);
        JPanel componentPanel = new JPanel();
        componentPanel.add(component, BorderLayout.WEST);
        panel.add(labelPanel);
        panel.add(componentPanel);
    }

    /**
     * Adds buttons for form, submit and cancel
     * @param frame frame for form
     * @param submitButton submit button
     * @param cancelButton submit button
     */

    private void addFormButtons(JPanel panel, JButton submitButton, JButton cancelButton)
    {
        panel.add(submitButton);
        panel.add(cancelButton);
    }

    /**
     * get employee info and create employee then update file
     * @param frame employee form
     * @param idField employee ID
     * @param fullNameField employee full Name
     * @param dobSpinner employee date of birth
     * @param startDateSpinner employee start date
     */

    private void handleAddEmployeeForm(JFrame frame, JTextField idField, JTextField fullNameField, JSpinner dobSpinner, JSpinner startDateSpinner)
    {
        try {
            // Validate and parse inputs
            int id = Integer.parseInt(idField.getText().trim());
            String fullName = fullNameField.getText().trim();
            if (fullName.isEmpty()) {
                throw new IllegalArgumentException("Full name cannot be empty.");
            }

            String[] splitName = fullName.split(" ");
            String firstName = splitName.length > 1 ? fullName.substring(0, fullName.lastIndexOf(" ")) : fullName;
            String lastName = splitName.length > 1 ? splitName[splitName.length - 1] : "";

            LocalDate dob = convertDateToLocalDate((Date) dobSpinner.getValue());
            LocalDate startDate = convertDateToLocalDate((Date) startDateSpinner.getValue());

            // Create and validate new employee
            Employee newEmployee = new Employee();
            newEmployee.setID(id);
            newEmployee.setName(firstName);
            newEmployee.setSurname(lastName);
            newEmployee.setDateOfBirth(dob);
            newEmployee.setStartDate(startDate);

            if (empList.contains(newEmployee)) {
                JOptionPane.showMessageDialog(frame, "Employee already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                empList.add(newEmployee);

                // Save to file
                LoadInfo cusFile = new LoadInfo();
                cusFile.createEmployee("employee.dat", this);

                JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * For spinner
     * @param date class: Date
     * @return class: LocalDate
     */
    private LocalDate convertDateToLocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Get ID and delete employee if match
     * @param frame delete employee form
     * @param idField employee ID
     */

    private void handleDeleteEmployeeForm(JFrame frame, JTextField idField)
    {
        try{
            int id = Integer.parseInt(idField.getText().trim());
            if(empList.contains(new Employee(id)))
            {
                empList.remove(new Employee(id));
                LoadInfo empFile = new LoadInfo();
                // Save to file
                empFile.createEmployee("employee.dat", this);
                JOptionPane.showMessageDialog(frame, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
            else
                JOptionPane.showMessageDialog(frame, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Enter ID, submit to delete
     */

    public void deleteEmployee()
    {
        JFrame frame = createFormFrame("Delete Employee");
        JPanel panel = new JPanel();

        //input field
        JTextField idField = createTextField();
        //Component of frame
        addFormRow(panel, "Employee ID:", idField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleDeleteEmployeeForm(frame, idField));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);

    }

    /**
     * If ID matches, employee details are printed
     * @param frame Employee Details frame
     * @param idField employee ID
     */

    public void handleListEmployeeDetails(JFrame frame, JTextField idField)
    {
        try{
            int id = Integer.parseInt(idField.getText().trim());
            if (empList.contains(new Employee(id))) {
                // Find the employee
                Employee printedEmployee = empList.get(empList.indexOf(new Employee(id)));

                JOptionPane.showMessageDialog(null, printedEmployee.toString(),
                        "Employee Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show error if employee is not found
                JOptionPane.showMessageDialog(null, "Employee not found!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Employee details frame
     */

    public void listEmployeeDetails() {
        JFrame frame = createFormFrame("List Employee Details");
        JPanel panel = new JPanel();

        //input field
        JTextField idField = createTextField();
        //Component of frame
        addFormRow(panel, "Employee ID:", idField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleListEmployeeDetails(frame, idField));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Gets the specific customer wth its specific typing by ID.
     * @param cus_id customer ID
     * @return subscribed or unsubscribed
     */

    public Customer customerType(int cus_id)
    {
        if(customerList.contains(new Subscribed(cus_id)))
            return this.customerList.get(customerList.indexOf(new Subscribed(cus_id)));
        else if(customerList.contains(new UnSubscribed(cus_id)))
            return this.customerList.get(customerList.indexOf(new UnSubscribed(cus_id)));
        else
            return null;
    }

    /**
     * Ask Deposit frame, the deposit will be asked from subscribed customer
     * @param parentFrame addCustomer form
     * @param newCustomer new subscribed customer being added
     */

    private void askDeposit(JFrame parentFrame, Subscribed newCustomer) {
        JFrame depositFrame = createFormFrame("Enter Deposit");
        JPanel panel = new JPanel();

        // Input field
        JTextField depositField = createTextField();
        addFormRow(panel, "Deposit Amount:", depositField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        submitButton.addActionListener(e -> {
            try {
                double deposit = Double.parseDouble(depositField.getText().trim());
                if (deposit < newCustomer.getMinDeposit()) {
                    JOptionPane.showMessageDialog(depositFrame, "Amount should be more than minimum deposit! Minimum deposit: " + newCustomer.getMinDeposit(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    newCustomer.setDepositPaid(deposit);
                    depositFrame.dispose();
                    addCustomerToList(parentFrame, newCustomer);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(depositFrame, "Invalid deposit amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> depositFrame.dispose());

        depositFrame.add(panel);
        depositFrame.setVisible(true);
    }

    /**
     * add new customer. If ID matches, invalid, customer already exists.
     * add customer Frame
     */
    public void addCustomer() {
        // Create the frame
        JFrame frame = createFormFrame("Add Customer");
        JPanel panel = new JPanel();

        // Create the input fields
        JTextField idField = createTextField();
        JTextField fullNameField = createTextField();
        JSpinner dobSpinner = createDateSpinner();
        JRadioButton subscribedButton = new JRadioButton("Subscribed");
        JRadioButton unsubscribedButton = new JRadioButton("Unsubscribed");
        ButtonGroup customerTypeGroup = new ButtonGroup();
        customerTypeGroup.add(subscribedButton);
        customerTypeGroup.add(unsubscribedButton);

        // Add components to the frame
        addFormRow(panel, "Customer ID:", idField);
        addFormRow(panel, "Full Name:", fullNameField);
        addFormRow(panel, "Date of Birth:", dobSpinner);
        addFormRow(panel, "Customer Type:", createRadioButtonPanel(subscribedButton, unsubscribedButton));

        // Add buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleAddCustomerForm(frame, idField, fullNameField, dobSpinner, subscribedButton.isSelected(), unsubscribedButton.isSelected()));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Initialize radio button
     * @param buttons buttons...
     * @return JPanel
     */

    private JPanel createRadioButtonPanel(JRadioButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JRadioButton button : buttons) {
            panel.add(button);
        }
        return panel;
    }

    /**
     * Form for add customer.
     * @param frame frame
     * @param idField customer ID
     * @param fullNameField customer full name
     * @param dobSpinner customer date of birth
     * @param isSubscribedSelected is subscribed?
     * @param isUnsubscribedSelected is unsubscribed?
     */

    private void handleAddCustomerForm(JFrame frame, JTextField idField, JTextField fullNameField, JSpinner dobSpinner, boolean isSubscribedSelected, boolean isUnsubscribedSelected) {
        try {
            // Validate and parse inputs
            int id = Integer.parseInt(idField.getText().trim());
            String fullName = fullNameField.getText().trim();
            if (fullName.isEmpty()) {
                throw new IllegalArgumentException("Full name cannot be empty.");
            }

            if (!isSubscribedSelected && !isUnsubscribedSelected) {
                throw new IllegalArgumentException("Please select a customer type (Subscribed or Unsubscribed).");
            }

            String[] splitName = fullName.split(" ");
            String firstName = splitName.length > 1 ? fullName.substring(0, fullName.lastIndexOf(" ")) : fullName;
            String lastName = splitName.length > 1 ? splitName[splitName.length - 1] : "";

            LocalDate dob = convertDateToLocalDate((Date) dobSpinner.getValue());

            // Create customer
            if (isSubscribedSelected) {
                Subscribed newCustomer = new Subscribed(id, dob, firstName, lastName, LocalDate.now(), LocalDate.now(), 0.0);
                askDeposit(frame, newCustomer);
            } else {
                UnSubscribed newCustomer = new UnSubscribed(id, dob, firstName, lastName, LocalDate.now());
                addCustomerToList(frame, newCustomer);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add teh customer to file
     * @param parentFrame add customer form
     * @param newCustomer new customer being added
     */

    private void addCustomerToList(JFrame parentFrame, Customer newCustomer) {
        if (customerList.contains(newCustomer)) {
            JOptionPane.showMessageDialog(parentFrame, "Customer already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            customerList.add(newCustomer);

            // Save to file
            LoadInfo customerFile = new LoadInfo();
            if (newCustomer instanceof Subscribed) {
                customerFile.createSubscribed("subscribed.dat", this);
            } else {
                customerFile.createUnSubscribed("unsubscribed.dat", this);
            }

            JOptionPane.showMessageDialog(parentFrame, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            parentFrame.dispose();
        }
    }

    /**
     * Delete customer if id matches
     * @param frame frame
     * @param idField customer ID
     */
    private void handleDeleteCustomerForm(JFrame frame, JTextField idField) {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            // Find the customer using `customerType`
            Customer customerToDelete = customerType(id);
            if (customerToDelete != null) {
                customerList.remove(customerToDelete);

                // Save the updated customer list to the correct file
                LoadInfo customerFile = new LoadInfo();
                if (customerToDelete instanceof Subscribed) {
                    customerFile.createSubscribed("subscribed.dat", this);
                } else {
                    customerFile.createUnSubscribed("unsubscribed.dat", this);
                }

                JOptionPane.showMessageDialog(frame, "Customer deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete customer form
     */

    public void deleteCustomer()
    {
        JFrame frame = createFormFrame("Delete Customer");
        JPanel panel = new JPanel();

        //input field
        JTextField idField = createTextField();
        //Component of frame
        addFormRow(panel, "Customer ID:", idField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleDeleteCustomerForm(frame, idField));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);

    }

    /**
     * If Id matches show details
     * @param frame frame
     * @param idField customer ID
     */

    public void handleGetCustomerDetails(JFrame frame, JTextField idField) {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Customer customer = customerType(id);

            if (customer != null) {
                // Display customer details using the toString method
                JOptionPane.showMessageDialog(null, customer.toString(),
                        "Customer Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show error if customer is not found
                JOptionPane.showMessageDialog(null, "Customer not found!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Enter id to show details
     */

    public void getCustomerDetails() {
        JFrame frame = createFormFrame("Get Customer Details");
        JPanel panel = new JPanel();

        // Input field
        JTextField idField = createTextField();
        addFormRow(panel, "Customer ID:", idField);

        // Add buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        // Submit button action
        submitButton.addActionListener(e -> handleGetCustomerDetails(frame, idField));

        // Cancel button action
        cancelButton.addActionListener(e -> frame.dispose());

        // Display the frame
        frame.setVisible(true);
    }

    // Order Management Methods
    /**
     * Form for order
     * @param parentFrame handlePutOrder
     * @param customer customer that is ordering
     */

    private void openOrderForm(JFrame parentFrame, Customer customer) {
        JFrame orderFrame = new JFrame("Create Order for " + customer.getName() + " " + customer.getSurname());
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setSize(600, 500);
        orderFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel quantityLabel = new JLabel("Item Quantity:");
        JTextField quantityField = createTextField();
        JLabel itemNameLabel = new JLabel("Item Name:");
        JTextField itemNameField = createTextField();
        JLabel cleaningTypeLabel = new JLabel("Cleaning Type:");
        JRadioButton wetCleanButton = new JRadioButton("Wet Cleaning");
        JRadioButton dryCleanButton = new JRadioButton("Dry Cleaning");
        ButtonGroup cleaningTypeGroup = new ButtonGroup();
        cleaningTypeGroup.add(wetCleanButton);
        cleaningTypeGroup.add(dryCleanButton);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = createTextField();
        JLabel notesLabel = new JLabel("Notes:");
        JTextField notesField = createTextField();

        JButton addItemButton = new JButton("Add Item");
        JButton finalizeOrderButton = new JButton("Finalize Order");
        JButton cancelButton = new JButton("Cancel");

        gbc.gridx = 0; gbc.gridy = 0;
        orderFrame.add(quantityLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        orderFrame.add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        orderFrame.add(itemNameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        orderFrame.add(itemNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        orderFrame.add(cleaningTypeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        JPanel cleaningTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        cleaningTypePanel.add(wetCleanButton);
        cleaningTypePanel.add(dryCleanButton);
        orderFrame.add(cleaningTypePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        orderFrame.add(priceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        orderFrame.add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        orderFrame.add(notesLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        orderFrame.add(notesField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        orderFrame.add(cancelButton, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        orderFrame.add(addItemButton, gbc);
        gbc.gridx = 2; gbc.gridy = 5;
        orderFrame.add(finalizeOrderButton, gbc);

        List<LaundryItem> itemList = new ArrayList<>();
        addItemButton.addActionListener(e -> handleAddItem(orderFrame, quantityField, itemNameField, wetCleanButton.isSelected(), priceField, notesField, itemList));
        finalizeOrderButton.addActionListener(e -> finalizeOrder(orderFrame, parentFrame, customer, itemList));
        cancelButton.addActionListener(e -> orderFrame.dispose());

        orderFrame.setVisible(true);
    }

    /**
     * Get item info, add to list of items
     * @param orderFrame order Frame
     * @param quantityField quantity of item
     * @param itemNameField name of item
     * @param isWetClean wet clean? or dryclean?
     * @param priceField price of item
     * @param notesField notes on item, can be empty
     * @param itemList list of items
     */


    private void handleAddItem(JFrame orderFrame, JTextField quantityField, JTextField itemNameField, boolean isWetClean, JTextField priceField, JTextField notesField, List<LaundryItem> itemList) {
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String itemName = itemNameField.getText().trim();
            int price = Integer.parseInt(priceField.getText().trim());
            String notes = notesField.getText().trim();

            if (itemName.isEmpty()) {
                throw new IllegalArgumentException("Item name cannot be empty.");
            }

            LaundryItem newItem = isWetClean ? new WetClean() : new DryClean();
            newItem.setQuantity(quantity);
            newItem.setType(itemName);
            newItem.setPrice(price);
            newItem.setNotes(notes);

            if (empList.isEmpty()) {
                JOptionPane.showMessageDialog(orderFrame, "No employees available! Add employees first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Random random = new Random();
            newItem.setAssigned(empList.get(random.nextInt(empList.size())));

            itemList.add(newItem);
            JOptionPane.showMessageDialog(orderFrame, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            quantityField.setText("");
            itemNameField.setText("");
            priceField.setText("");
            notesField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(orderFrame, "Invalid numeric input. Please enter valid values for quantity and price.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(orderFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * When user added items, they can finalize their order.
     * @param orderFrame order Frame
     * @param parentFrame openOrderForm
     * @param customer customer that is ordering
     * @param itemList list of items in the order
     */

    private void finalizeOrder(JFrame orderFrame, JFrame parentFrame, Customer customer, List<LaundryItem> itemList) {
        if (itemList.isEmpty()) {
            JOptionPane.showMessageDialog(orderFrame, "No items added to the order.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel paymentLabel = new JLabel("Is the order paid?");
        JRadioButton paidYes = new JRadioButton("Yes");
        JRadioButton paidNo = new JRadioButton("No");
        ButtonGroup group = new ButtonGroup();
        group.add(paidYes);
        group.add(paidNo);
        panel.add(paymentLabel);
        panel.add(paidYes);
        panel.add(paidNo);

        int result = JOptionPane.showConfirmDialog(null, panel, "Finalize Order", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean isPaid = paidYes.isSelected();

            Order newOrder = new Order();

            newOrder.setItemList(itemList);
            newOrder.setOrderDate(LocalDate.now());
            newOrder.setPaidStatus(isPaid);
            customer.putOrder(newOrder);

            LoadInfo cusFile = new LoadInfo();
            cusFile.createSubscribed("subscribed.dat", this);
            cusFile.createUnSubscribed("unsubscribed.dat", this);

            JOptionPane.showMessageDialog(orderFrame, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            orderFrame.dispose();
            parentFrame.dispose();
        }
    }

    /**
     * If customer id matches, open order form
     * @param frame frame
     * @param customerIdField customer ID
     */
    private void handlePutOrder(JFrame frame, JTextField customerIdField) {
        try {
            int customerId = Integer.parseInt(customerIdField.getText().trim());
            Customer customer = customerType(customerId);

            if (customer != null) {
                openOrderForm(frame, customer);
            } else {
                JOptionPane.showMessageDialog(frame, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Put order frame
     */
    public void putOrder() {
        JFrame frame = createFormFrame("Put Order");
        JPanel panel = new JPanel();

        JTextField customerIdField = createTextField();
        addFormRow(panel, "Customer ID:", customerIdField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addFormButtons(panel, submitButton, cancelButton);

        submitButton.addActionListener(e -> handlePutOrder(frame, customerIdField));
        cancelButton.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * If customer ID matches and a customer has put an order that matches orderDate
     * Order details are printed
     * returns void
     */

    public void getCustomerOrderDetails() {
        // Create the frame
        JFrame frame = new JFrame("Customer Order Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        // Input fields
        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JLabel dateLabel = new JLabel("Order Date (dd/MM/yyyy):");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        frame.add(idLabel);
        frame.add(idField);
        frame.add(dateLabel);
        frame.add(dateSpinner);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        frame.add(submitButton);
        frame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                LocalDate orderDate = convertDateToLocalDate((Date) dateSpinner.getValue());

                Customer customer = customerList.stream()
                        .filter(c -> c.getID() == id)
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    List<Order> orders = customer.getOrders();
                    List<Order> matchingOrders = orders.stream()
                            .filter(order -> order.getOrderDate().equals(orderDate))
                            .toList();

                    if (!matchingOrders.isEmpty()) {
                        StringBuilder orderDetails = new StringBuilder("Orders on " + orderDate + ":\n");
                        for (Order order : matchingOrders) {
                            orderDetails.append(order).append("\n");
                        }
                        JOptionPane.showMessageDialog(frame, orderDetails.toString(), "Order Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No orders found for the specified date.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    /**
     *If customer ID matches and a customer has put an order that matches orderDate
     *Total cost for that order is printed (sum of all price of items)

     * returns void
     */

    public void getCustomerOrderTotalCost() {
        // Create the frame
        JFrame frame = new JFrame("Customer Order Total Cost");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        // Input fields
        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JLabel dateLabel = new JLabel("Order Date (dd/MM/yyyy):");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        frame.add(idLabel);
        frame.add(idField);
        frame.add(dateLabel);
        frame.add(dateSpinner);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        frame.add(submitButton);
        frame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                LocalDate orderDate = convertDateToLocalDate((Date) dateSpinner.getValue());

                Customer customer = customerList.stream()
                        .filter(c -> c.getID() == id)
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    List<Order> orders = customer.getOrders();
                    OptionalDouble totalCost = orders.stream()
                            .filter(order -> order.getOrderDate().equals(orderDate))
                            .mapToDouble(Order::totalOrderCost)
                            .reduce(Double::sum);

                    if (totalCost.isPresent()) {
                        JOptionPane.showMessageDialog(frame, "Total Cost: " + totalCost.getAsDouble(), "Total Cost", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No orders found for the specified date.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    /**
     * Prints list of all employees
     * no parameters
     * returns void
     */

    public void listEmployees() {
        // Create frame for listing employees
        JFrame employeeFrame = new JFrame("List of Employees");
        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeFrame.setSize(800, 400);
        employeeFrame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Employee List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        employeeFrame.add(titleLabel, BorderLayout.NORTH);

        // Table to display employee data
        String[] columnNames = {
                "ID", "First Name", "Last Name", "Date of Birth",
                "Start Date", "Nationality", "Work Permit End Date"
        };

        Object[][] data = new Object[empList.size()][columnNames.length];

        for (int i = 0; i < empList.size(); i++) {
            Employee employee = empList.get(i);
            data[i][0] = employee.getID();
            data[i][1] = employee.getName();
            data[i][2] = employee.getSurname();
            data[i][3] = employee.getDateOfBirth();
            data[i][4] = employee.getStartDate();
            data[i][5] = employee.getNationality(); // Assuming Employee class has a getNationality() method
            data[i][6] = employee.getWorkPermitEndDate(); // Assuming Employee class has a getWorkPermitEndDate() method
        }

        JTable employeeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        employeeFrame.add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> employeeFrame.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        employeeFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        employeeFrame.setVisible(true);
    }

    /**
     * Prints list of all customers
     */

    public void listCustomers() {
        // Create frame for listing customers
        JFrame customerFrame = new JFrame("List of Customers");
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.setSize(800, 400);
        customerFrame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Customer List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        customerFrame.add(titleLabel, BorderLayout.NORTH);

        // Table to display customer data
        String[] columnNames = {
                "ID", "First Name", "Last Name", "Date of Birth",
                "Registration Date", "Type", "Deposit Paid", "Subscription Date"
        };

        Object[][] data = new Object[customerList.size()][columnNames.length];

        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            data[i][0] = customer.getID();
            data[i][1] = customer.getName();
            data[i][2] = customer.getSurname();
            data[i][3] = customer.getDateOfBirth();
            data[i][4] = customer.getRegistrationDate();
            data[i][5] = customer instanceof Subscribed ? "Subscribed" : "Unsubscribed";
            data[i][6] = customer instanceof Subscribed ? ((Subscribed) customer).getDepositPaid() : "N/A";
            data[i][7] = customer instanceof Subscribed ? ((Subscribed) customer).getSubscriptionDate() : "N/A";
        }

        JTable customerTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        customerFrame.add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> customerFrame.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        customerFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        customerFrame.setVisible(true);
    }

    /**
     * Exit statement
     */

    public void exit()
    {
        System.out.println("Bye bye!");
        System.exit(0);
    }

    /**
     * Add health inspection details for employee
     */

    public void addHealthInspection() {
        JFrame frame = new JFrame("Add Health Inspection");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel idLabel = new JLabel("Employee ID:");
        JTextField idField = new JTextField();
        JLabel dateLabel = new JLabel("Inspection Date:");
        JSpinner dateSpinner = createDateSpinner();
        JLabel reportLabel = new JLabel("Health Report:");
        JTextField reportField = new JTextField();

        frame.add(idLabel);
        frame.add(idField);
        frame.add(dateLabel);
        frame.add(dateSpinner);
        frame.add(reportLabel);
        frame.add(reportField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        frame.add(submitButton);
        frame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String report = reportField.getText().trim();

                Employee employee = empList.stream()
                        .filter(emp -> emp.getID() == id)
                        .findFirst()
                        .orElse(null);

                if (employee != null) {
                    LocalDate inspectionDate = convertDateToLocalDate((Date) dateSpinner.getValue());
                    employee.getHealthInspection().put(inspectionDate, report);
                    LoadInfo empInfo = new LoadInfo();
                    empInfo.createEmployee("employee.dat", this);
                    JOptionPane.showMessageDialog(frame, "Health inspection added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    /**
     * show health inspection details
     */

    public void printHealthInspection() {
        JFrame frame = new JFrame("Print Health Inspection");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel idLabel = new JLabel("Employee ID:");
        JTextField idField = new JTextField();

        frame.add(idLabel);
        frame.add(idField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        frame.add(submitButton);
        frame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                Employee employee = empList.stream()
                        .filter(emp -> emp.getID() == id)
                        .findFirst()
                        .orElse(null);

                if (employee != null) {
                    Map<LocalDate, String> healthInspectionMap = employee.getHealthInspection();
                    StringBuilder formattedOutput = new StringBuilder("Health Inspection Details:\n");

                    healthInspectionMap.forEach((date, report) -> {
                        formattedOutput.append("Date: ").append(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
                                .append("Report: ").append(report).append("\n\n");
                    });

                    JOptionPane.showMessageDialog(frame, formattedOutput.toString(), "Health Inspection", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    /**
     * Compare customer loyalty, return the higher money maker
     */

    public void compareCustomerLoyalty() {
        JFrame frame = new JFrame("Compare Customer Loyalty");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel id1Label = new JLabel("Customer 1 ID:");
        JTextField id1Field = new JTextField();
        JLabel id2Label = new JLabel("Customer 2 ID:");
        JTextField id2Field = new JTextField();

        frame.add(id1Label);
        frame.add(id1Field);
        frame.add(id2Label);
        frame.add(id2Field);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        frame.add(submitButton);
        frame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                int id1 = Integer.parseInt(id1Field.getText().trim());
                int id2 = Integer.parseInt(id2Field.getText().trim());

                Customer customer1 = customerType(id1);
                Customer customer2 = customerType(id2);

                if (customer1 != null && customer2 != null) {
                    double income1 = customer1.calculateTotalOrderIncome();
                    double income2 = customer2.calculateTotalOrderIncome();

                    String message;
                    if (income1 > income2) {
                        message = "Customer " + id1 + " brings more income than Customer " + id2 + ".\n" +
                                "Income of Customer " + id1 + ": " + income1 + "\n" +
                                "Income of Customer " + id2 + ": " + income2;
                    } else if (income1 < income2) {
                        message = "Customer " + id2 + " brings more income than Customer " + id1 + ".\n" +
                                "Income of Customer " + id1 + ": " + income1 + "\n" +
                                "Income of Customer " + id2 + ": " + income2;
                    } else {
                        message = "Both customers bring the same income.\n" +
                                "Income of Customer " + id1 + ": " + income1 + "\n" +
                                "Income of Customer " + id2 + ": " + income2;
                    }

                    JOptionPane.showMessageDialog(frame, message, "Customer Loyalty Comparison", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "One or both customers not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }


}

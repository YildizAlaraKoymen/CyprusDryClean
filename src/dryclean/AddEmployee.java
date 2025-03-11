package dryclean;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

//TODO: Delete id field and make it auto increment
public class AddEmployee extends JPanel{
    AddEmployee(){}
    AddEmployee(JFrame frame)
    {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = makeGbc(0, 0);
        JLabel FullNameLabel = new JLabel("Full name: ");
        add(FullNameLabel, gbc);

        JPanel panel = new JPanel();
        JTextField FullNameField = new JTextField(15);
        panel.add(FullNameField);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 0);
        add(panel, gbc);

        gbc = makeGbc(0, 1);
        JLabel DOBLabel = new JLabel("Date of Birth: ");
        add(FullNameLabel, gbc);

        panel = new JPanel();
        JSpinner DOBSpinner = createDateSpinner();
        panel.add(DOBSpinner);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 1);
        add(panel, gbc);

        gbc = makeGbc(0, 2);
        JLabel StartTimeLabel = new JLabel("Start Time: ");
        add(StartTimeLabel, gbc);

        panel = new JPanel();
        JSpinner StartTimeSpinner = createDateSpinner();
        panel.add(StartTimeSpinner);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 2);
        add(panel, gbc);

        gbc = makeGbc(0, 3);
        JLabel NationalityLabel = new JLabel("Nationality: ");
        add(NationalityLabel, gbc);

        panel = new JPanel();
        JTextField nationalityField = new JTextField();
        panel.add(nationalityField);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 3);
        add(panel, gbc);

        gbc = makeGbc(0, 4);
        JLabel workPermitLabel = new JLabel("Work Permit Date: ");
        add(workPermitLabel, gbc);

        panel = new JPanel();
        JSpinner workPermitSpinner = createDateSpinner();
        panel.add(workPermitSpinner);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 4);
        add(panel, gbc);

        gbc = makeGbc(0, 5);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleAddEmployee(FullNameField, DOBSpinner, StartTimeSpinner, nationalityField, workPermitSpinner));
        add(submitButton, gbc);

        gbc = makeGbc(1, 5);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.dispose());
        add(cancelButton, gbc);
    }
    private JSpinner createDateSpinner()
    {
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        return dateSpinner;
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
    protected void createAddEmployeeUI(){
        JFrame frame = new JFrame("Add Employee");
        frame.getContentPane().add(new AddEmployee(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
//    private void handleAddEmployee(JTextField fullNameField, JSpinner dobSpinner, JSpinner startDateSpinner)
//    {
//        try {
//            // Validate and parse inputs
//            int id = Integer.parseInt(idField.getText().trim());
//            String fullName = fullNameField.getText().trim();
//            if (fullName.isEmpty()) {
//                throw new IllegalArgumentException("Full name cannot be empty.");
//            }
//
//            String[] splitName = fullName.split(" ");
//            String firstName = splitName.length > 1 ? fullName.substring(0, fullName.lastIndexOf(" ")) : fullName;
//            String lastName = splitName.length > 1 ? splitName[splitName.length - 1] : "";
//
//            LocalDate dob = convertDateToLocalDate((Date) dobSpinner.getValue());
//            LocalDate startDate = convertDateToLocalDate((Date) startDateSpinner.getValue());
//
//            // Create and validate new employee
//            Employee newEmployee = new Employee();
//            newEmployee.setID(id);
//            newEmployee.setName(firstName);
//            newEmployee.setSurname(lastName);
//            newEmployee.setDateOfBirth(dob);
//            newEmployee.setStartDate(startDate);
//
//            if (empList.contains(newEmployee)) {
//                JOptionPane.showMessageDialog(frame, "Employee already exists.", "Error", JOptionPane.ERROR_MESSAGE);
//            } else {
//                empList.add(newEmployee);
//
//                // Save to file
//                LoadInfo cusFile = new LoadInfo();
//                cusFile.createEmployee("employee.dat", this);
//
//                JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                frame.dispose();
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private void handleAddEmployee(JTextField fullNameField, JSpinner dobSpinner, JSpinner startTimeSpinner, JTextField nationalityField, JSpinner workPermitSpinner)
    {
        JFrame errorFrame = new JFrame();
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try{
            String fullName = fullNameField.getText().trim();
            String nationality = nationalityField.getText().trim();
            LocalDate dob = convertDateToLocalDate((Date) dobSpinner.getValue());
            LocalDate startTime = convertDateToLocalDate((Date) startTimeSpinner.getValue());
            LocalDate workPermit = convertDateToLocalDate((Date) workPermitSpinner.getValue());
            //Get name full name = name, last name.
            String[] splitName = fullName.split(" ");
            String firstName = splitName.length > 1 ? fullName.substring(0, fullName.lastIndexOf(" ")) : fullName;
            String lastName = splitName.length > 1 ? splitName[splitName.length - 1] : "";
            if(fullName.isEmpty()){
                throw new IllegalArgumentException("Full name must be entered.");
            }
            else if(dob == null){
                throw new IllegalArgumentException("Date of birth must be selected.");
            }
            else if(startTime == null){
                throw new IllegalArgumentException("Start time must be selected.");
            }
            //Add to database
            try{
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/CyprusDryCleanDB", "drycleanAdmin", "1234");
                String query = "INSERT INTO Employee(dateOfBirth, name, surname, startDate, nationality, workPermitEndDate) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = c.prepareStatement(query);
                preparedStatement.setString(1, dob.toString());
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3,lastName);
                preparedStatement.setString(4,startTime.toString());
                preparedStatement.setString(5,nationality);
                preparedStatement.setString(6, workPermit.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }catch(RuntimeException e){
            System.exit(1);
        }

    }
    private LocalDate convertDateToLocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}

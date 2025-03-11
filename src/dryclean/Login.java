package dryclean;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

//TODO: FIX VALIDATE PASSWORD
//TODO: FIX LOGIN IN GENERAL
public class Login extends JPanel {
    protected Login(){}
    protected Login(JFrame frame){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = makeGbc(0, 0);
        JLabel userNameLabel = new JLabel("Username: ");
        add(userNameLabel, gbc);

        JPanel panel = new JPanel();
        JTextField userNameTextField = new JTextField(15);
        panel.add(userNameTextField);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 0);
        add(panel, gbc);

        gbc = makeGbc(0, 1);
        JLabel passwordLabel = new JLabel("Password: ");
        add(passwordLabel, gbc);

        panel = new JPanel();
        JPasswordField passwordField = new JPasswordField(15);
        panel.add(passwordField);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 1);
        add(panel, gbc);

        gbc = makeGbc(0, 3);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleLogin(userNameTextField, passwordField));
        add(submitButton, gbc);

        gbc = makeGbc(1, 3);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> frame.dispose());
        add(cancelButton, gbc);
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
    protected void createLoginUI(){
        JFrame frame = new JFrame("Login");
        frame.getContentPane().add(new Login(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void handleLogin(JTextField userNameTextField, JPasswordField passwordField){
        JFrame errorFrame = new JFrame();
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try{
            String userName = userNameTextField.getText().trim();
            char[] password = passwordField.getPassword();

            if(userName.isEmpty())
            {
                throw new IllegalArgumentException("User name cannot be empty.");
            }
            else if(password.length == 0)
            {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            //Get username and password
            try{
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/CyprusDryCleanDB", "drycleanAdmin", "1234");
                String query = "SELECT userName, password FROM Admin WHERE userName = ?";
                PreparedStatement preparedStatement = c.prepareStatement(query);
                preparedStatement.setString(1, userName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.next())
                    throw new IllegalArgumentException("The userName or password is incorrect.");

                VerifyPassword vp = new VerifyPassword();
                while(resultSet.next()){
                    String storedPassword = resultSet.getString("password");
                    if(!vp.validatePassword(new String(password), storedPassword))
                        throw new IllegalArgumentException("The userName or password is incorrect.");
                    else{
                        System.out.println("hfsjkfaö");
                        Menu menuPanel = new Menu();
                        menuPanel.createMenuUI();
                    }
                }

            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }


        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }catch (RuntimeException e){
            System.exit(1);
        }

    }
}

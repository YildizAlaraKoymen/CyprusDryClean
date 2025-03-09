package dryclean;

import javax.swing.*;
import java.awt.*;

public class FormLayout extends JPanel {
    protected FormLayout(){}
    protected FormLayout(JFrame frame){
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

        gbc = makeGbc(0, 2);
        JLabel passwordLabel2 = new JLabel("Confirm password: ");
        add(passwordLabel2, gbc);

        panel = new JPanel();
        JPasswordField passwordField2 = new JPasswordField(15);
        panel.add(passwordField2);
        panel.setBorder(BorderFactory.createEtchedBorder());
        gbc = makeGbc(1, 2);
        add(panel, gbc);

        gbc = makeGbc(0, 3);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleRegister(userNameTextField, passwordField, passwordField2));
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
    public void createRegisterUI(){
        JFrame frame = new JFrame("Register");
        frame.getContentPane().add(new FormLayout(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }
    public void handleRegister(JTextField userNameField, JPasswordField passwordField, JPasswordField password2Field)
    {
        JFrame errorFrame = new JFrame();
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try{
            String userName = userNameField.getText().trim();

            if (userName.isEmpty()) {
                throw new IllegalArgumentException("User name cannot be empty.");
            }
            else if(passwordField.getPassword().length == 0)
            {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            else if(password2Field.getPassword().length == 0)
            {
                throw new IllegalArgumentException("Password must be confirmed.");
            }
            if(!passwordField.equals(password2Field)){
                JOptionPane.showMessageDialog(errorFrame, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //TODO: ADD USERNAME & ENCRYPT AND ADD PASSWORD TO DATABASE

        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

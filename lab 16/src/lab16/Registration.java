package lab16;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame {

    private JTextField txtName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private LoginOrRegister loginOrRegister;

    public Registration(LoginOrRegister loginOrRegister) {
        this.loginOrRegister = loginOrRegister;
        setTitle("Register");
        setSize(300, 250); // Set size of the window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create labels
        JLabel lblName = new JLabel("Name:");
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        // Create text fields
        txtName = new JTextField(15);
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);

        // Create submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Validate username
                if (validateUsername(username)) {
                    UserCredentials.getInstance().setCredentials(username, password);
                    JOptionPane.showMessageDialog(Registration.this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    int choice = JOptionPane.showConfirmDialog(Registration.this, "Do you want to register another user?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Clear the registration form
                        txtName.setText("");
                        txtUsername.setText("");
                        txtPassword.setText("");
                    } else {
                        dispose(); // Close registration window
                        loginOrRegister.setVisible(true); // Show login window
                    }
                } else {
                    JOptionPane.showMessageDialog(Registration.this, "Invalid Username! Must be no more than 5 characters and contain '_'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create panel with vertical layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

        // Add components to the panel
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSubmit);

        // Add panel to the frame
        add(panel);
    }

    // Method to validate username
    private boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }
}
package lab16;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends javax.swing.JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSubmit;

    public login() {
        initComponents();
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnSubmit = new JButton("Submit");

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        JPanel panel = new JPanel();
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSubmit);

        add(panel);
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Check credentials
        if (UserCredentials.getInstance().checkCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to the welcome screen or main application
            new welcome().setVisible(true);
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new login().setVisible(true));
    }
}
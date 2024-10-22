package lab16;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOrRegister extends JFrame {
    
    public LoginOrRegister() {
        setTitle("Welcome"); // Set the title of the window
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        // Action listener for the Login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new login().setVisible(true);
                dispose(); // Close the LoginOrRegister window
            }
        });

        // Action listener for the Register button
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Registration(LoginOrRegister.this).setVisible(true);
                // Do not dispose the LoginOrRegister window
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnLogin);
        panel.add(btnRegister);
        
        add(panel);
    }

    // Main method to launch the LoginOrRegister window
    public static void main(String[] args) {
        // Set the look and feel for the application
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}}

    
package lab16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lab16.Task;

/**
 * Welcome class for the EasyKanban application.
 * Displays a welcome message and allows users to manage tasks.
 */
public class welcome extends JFrame {
    private String[] developers = new String[100];
    private String[] taskNames = new String[100];
    private String[] taskIDs = new String[100];
    private int[] taskDurations = new int[100];
    private String[] taskStatuses = new String[100];
    private int taskCounter = 0; // Counter to track task numbers
// Counter to track task numbers

    public welcome() {
        setTitle("EasyKanban");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to EasyKanban");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a custom menu panel
        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create buttons
        JButton addButton = new JButton("Add Task");
        JButton reportButton = new JButton("Show Report");
        JButton quitButton = new JButton("Quit");

        // Add action listeners
        addButton.addActionListener(e -> showAddTaskDialog());
        reportButton.addActionListener(e -> showReport());
        quitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the menu panel
        menuPanel.add(addButton, gbc);
        menuPanel.add(reportButton, gbc);
        menuPanel.add(quitButton, gbc);

        // Add menu panel to the main panel
        panel.add(menuPanel, BorderLayout.CENTER);
        add(panel);

        pack();
    }


private void showAddTaskDialog() {
    JFrame dialog = new JFrame("Add Task");
    dialog.setSize(400, 300);
    dialog.setLocationRelativeTo(null);
    dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JTextField taskNameField = new JTextField();
    JTextField taskDescriptionField = new JTextField();
    JTextField durationField = new JTextField();
    JComboBox<String> taskStatusComboBox = new JComboBox<>(new String[]{"To Do", "Done", "Doing"});

    panel.add(new JLabel("Task Name:"));
    panel.add(taskNameField);
    panel.add(Box.createVerticalStrut(5));
    panel.add(new JLabel("Task Description:"));
    panel.add(taskDescriptionField);
    panel.add(Box.createVerticalStrut(5));
    panel.add(new JLabel("Task Duration (hours):"));
    panel.add(durationField);
    panel.add(Box.createVerticalStrut(5));
    panel.add(new JLabel("Task Status:"));
    panel.add(taskStatusComboBox);
    panel.add(Box.createVerticalStrut(10));

    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(e -> {
        String taskName = taskNameField.getText().trim();
        String taskDescription = taskDescriptionField.getText().trim();
        String durationInput = durationField.getText().trim();
        String taskStatus = (String) taskStatusComboBox.getSelectedItem();

        // Validate input
        if (taskName.isEmpty() || taskDescription.isEmpty() || durationInput.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int taskDuration;
        try {
            taskDuration = Integer.parseInt(durationInput);
            if (taskDuration < 0) {
                throw new NumberFormatException(); // Handle negative duration
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Please enter a valid positive number for task duration.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and add the task
        Task task = new Task(taskName, taskDescription, "Unassigned", taskDuration, "Unassigned");
        Task.add(task); // Add the task to the list

        // Update success message
        JOptionPane.showMessageDialog(dialog, "Task created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dialog.dispose(); // Close the dialog
    });

    panel.add(submitButton);
    dialog.add(panel);
    dialog.setVisible(true);
}

    private void showReport() {
        StringBuilder report = new StringBuilder();
        report.append("Task Report:\n");
        for (int i = 0; i < taskCounter; i++) {
            report.append("Task Name: ").append(taskNames[i]).append("\n");
            report.append("Task ID: ").append(taskIDs[i]).append("\n");
            report.append("Developer: ").append(developers[i]).append("\n");
            report.append("Task Duration: ").append(taskDurations[i]).append(" hours\n");
            report.append("Task Status: ").append(taskStatuses[i]).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new welcome().setVisible(true);
            }
        });
    }
}
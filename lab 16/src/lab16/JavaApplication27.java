package lab16;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class JavaApplication27 {
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
        }

        // Start the application by showing the LoginOrRegister window
        SwingUtilities.invokeLater(() -> {
            new LoginOrRegister().setVisible(true);
        });
    }
}
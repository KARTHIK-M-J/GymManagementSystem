package gymmanagement;

import javax.swing.SwingUtilities;

public class GymManagementSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
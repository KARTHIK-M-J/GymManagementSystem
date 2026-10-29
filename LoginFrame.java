
package gymmanagement;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Gym Management System - Login");
        setSize(400, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        JLabel title = new JLabel("GYM MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(title);
        panel.add(new JLabel(""));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        loginButton.addActionListener(e -> login());

        add(panel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Demonstration login only.
        if (username.equals("admin")
                && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                this, "Login successful!"
            );

            new DashboardFrame().setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(
                this, "Invalid username or password."
            );
        }
    }
}

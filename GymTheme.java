package gymmanagement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class GymTheme {

public static final Color BACKGROUND = new Color(18, 18, 18);
public static final Color CARD = new Color(31, 31, 31);
public static final Color GREEN = new Color(57, 255, 20);
public static final Color TEXT = new Color(245, 245, 245);
public static final Color MUTED = new Color(165, 165, 165);

public static final Font TITLE_FONT =
        new Font("Arial", Font.BOLD, 24);

public static final Font NORMAL_FONT =
        new Font("Arial", Font.PLAIN, 14);

public static void apply() {
    UIManager.put("Panel.background", BACKGROUND);
    UIManager.put("OptionPane.background", CARD);
    UIManager.put("OptionPane.messageForeground", TEXT);
    UIManager.put("Button.background", CARD);
    UIManager.put("Button.foreground", GREEN);
    UIManager.put("Label.foreground", TEXT);
    UIManager.put("TextField.background", CARD);
    UIManager.put("TextField.foreground", TEXT);
    UIManager.put("PasswordField.background", CARD);
    UIManager.put("PasswordField.foreground", TEXT);
}

public static void styleButton(JButton button) {
    button.setBackground(CARD);
    button.setForeground(GREEN);
    button.setFont(new Font("Arial", Font.BOLD, 15));
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GREEN, 1),
            BorderFactory.createEmptyBorder(12, 18, 12, 18)
    ));
    button.setOpaque(true);
}

public static void styleField(JTextField field) {
    field.setBackground(CARD);
    field.setForeground(TEXT);
    field.setCaretColor(GREEN);
    field.setFont(NORMAL_FONT);
    field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                    new Color(75, 75, 75), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
    ));
}

public static void stylePassword(JPasswordField field) {
    field.setBackground(CARD);
    field.setForeground(TEXT);
    field.setCaretColor(GREEN);
    field.setFont(NORMAL_FONT);
    field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                    new Color(75, 75, 75), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
    ));
}

}
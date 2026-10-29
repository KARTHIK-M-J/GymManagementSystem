package gymmanagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JPanel mainPanel;
    private JPanel contentPanel;

    public DashboardFrame() {

        // Apply global dark theme
        GymTheme.apply();

        setTitle("GYMFIT | Management Dashboard");
        setSize(1000, 650);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main background
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(GymTheme.BACKGROUND);

        // ---------------- HEADER ----------------
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GymTheme.CARD);
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel brandPanel = new JPanel(new GridLayout(2, 1));
        brandPanel.setOpaque(false);

        JLabel logo = new JLabel("GYMFIT");
        logo.setForeground(GymTheme.GREEN);
        logo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel("GYM MANAGEMENT SYSTEM");
        subtitle.setForeground(GymTheme.MUTED);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 11));

        brandPanel.add(logo);
        brandPanel.add(subtitle);

        JLabel adminLabel = new JLabel("ADMIN PANEL");
        adminLabel.setForeground(GymTheme.GREEN);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));

        header.add(brandPanel, BorderLayout.WEST);
        header.add(adminLabel, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ---------------- CENTER CONTENT ----------------
        contentPanel = new JPanel();
        contentPanel.setBackground(GymTheme.BACKGROUND);
        contentPanel.setLayout(new BorderLayout(15, 25));
        contentPanel.setBorder(new EmptyBorder(35, 35, 35, 35));

        // Welcome section
        JPanel welcomePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        welcomePanel.setOpaque(false);

        JLabel welcomeTitle = new JLabel("Welcome Back, Admin");
        welcomeTitle.setForeground(GymTheme.TEXT);
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 27));

        JLabel welcomeSubtitle = new JLabel(
                "Manage your gym members, payments and daily attendance.");
        welcomeSubtitle.setForeground(GymTheme.MUTED);
        welcomeSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        welcomePanel.add(welcomeTitle);
        welcomePanel.add(welcomeSubtitle);

        // Feature cards
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        cardsPanel.setOpaque(false);

        JPanel memberCard = createCard(
                "01",
                "MEMBERS",
                "Register and manage gym members",
                "Manage Members"
        );

        JPanel paymentCard = createCard(
                "02",
                "PAYMENTS",
                "Track fees and payment history",
                "Manage Payments"
        );

        JPanel attendanceCard = createCard(
                "03",
                "ATTENDANCE",
                "Record and view daily check-ins",
                "Manage Attendance"
        );

        cardsPanel.add(memberCard);
        cardsPanel.add(paymentCard);
        cardsPanel.add(attendanceCard);

        // Bottom information panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JLabel statusLabel = new JLabel(
                "● SYSTEM READY   |   MYSQL DATABASE");
        statusLabel.setForeground(GymTheme.GREEN);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton logoutButton = new JButton("LOG OUT");
        GymTheme.styleButton(logoutButton);
        logoutButton.setPreferredSize(new Dimension(140, 45));

        logoutButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        bottomPanel.add(statusLabel, BorderLayout.WEST);
        bottomPanel.add(logoutButton, BorderLayout.EAST);

        // Arrange content
        JPanel topContent = new JPanel(new BorderLayout());
        topContent.setOpaque(false);
        topContent.add(welcomePanel, BorderLayout.NORTH);
        topContent.add(cardsPanel, BorderLayout.CENTER);

        contentPanel.add(topContent, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel(
                "  GYMFIT  |  STAY STRONG. STAY CONSISTENT.  ");
        footer.setForeground(GymTheme.MUTED);
        footer.setBackground(GymTheme.CARD);
        footer.setOpaque(true);
        footer.setBorder(new EmptyBorder(12, 15, 12, 15));
        footer.setFont(new Font("Arial", Font.PLAIN, 11));

        mainPanel.add(footer, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // Creates a dashboard feature card
    private JPanel createCard(
            String number,
            String heading,
            String description,
            String buttonText) {

        JPanel card = new JPanel();
        card.setBackground(GymTheme.CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(65, 65, 65), 1),
                new EmptyBorder(22, 18, 22, 18)
        ));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setForeground(GymTheme.GREEN);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 30));
        numberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel headingLabel = new JLabel(heading);
        headingLabel.setForeground(GymTheme.TEXT);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 17));
        headingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setForeground(GymTheme.MUTED);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 13));
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionArea.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 65));

        JButton openButton = new JButton(buttonText);
        GymTheme.styleButton(openButton);
        openButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        openButton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 48));
        openButton.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Keep your existing screens connected
        openButton.addActionListener(e -> {
            switch (buttonText) {
                case "Manage Members":
                    new MemberFrame().setVisible(true);
                    break;

                case "Manage Payments":
                    new PaymentFrame().setVisible(true);
                    break;

                case "Manage Attendance":
                    new AttendanceFrame().setVisible(true);
                    break;
            }
        });

        card.add(numberLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(headingLabel);
        card.add(Box.createVerticalStrut(12));
        card.add(descriptionArea);
        card.add(Box.createVerticalGlue());
        card.add(Box.createVerticalStrut(20));
        card.add(openButton);

        return card;
    }
}
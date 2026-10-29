
package gymmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MemberFrame extends JFrame {

    private JTextField nameField, phoneField, emailField;
    private JTextField startField, endField, feeField;
    private JComboBox<String> genderBox, planBox;
    private DefaultTableModel model;

    public MemberFrame() {
        setTitle("Gym Management - Members");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(4, 4, 8, 8));
        form.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        startField = new JTextField();
        endField = new JTextField();
        feeField = new JTextField();

        genderBox = new JComboBox<>(
            new String[]{"Male", "Female", "Other"}
        );
        planBox = new JComboBox<>(
            new String[]{"Monthly", "Quarterly", "Yearly"}
        );

        form.add(new JLabel("Full Name:"));
        form.add(nameField);
        form.add(new JLabel("Gender:"));
        form.add(genderBox);

        form.add(new JLabel("Phone:"));
        form.add(phoneField);
        form.add(new JLabel("Email:"));
        form.add(emailField);

        form.add(new JLabel("Start Date (YYYY-MM-DD):"));
        form.add(startField);
        form.add(new JLabel("End Date (YYYY-MM-DD):"));
        form.add(endField);

        form.add(new JLabel("Plan:"));
        form.add(planBox);
        form.add(new JLabel("Fee:"));
        form.add(feeField);

        JButton addButton = new JButton("Add Member");
        JButton refreshButton = new JButton("Refresh");
        JButton clearButton = new JButton("Clear");

        JPanel actions = new JPanel();
        actions.add(addButton);
        actions.add(refreshButton);
        actions.add(clearButton);

        model = new DefaultTableModel(
            new String[]{
                "ID", "Name", "Phone", "Email",
                "Plan", "Start Date", "End Date", "Fee", "Status"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);

        addButton.addActionListener(e -> addMember());
        refreshButton.addActionListener(e -> loadMembers());
        clearButton.addActionListener(e -> clearFields());

        JPanel north = new JPanel(new BorderLayout());
        north.add(form, BorderLayout.CENTER);
        north.add(actions, BorderLayout.SOUTH);

        add(north, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadMembers();
    }

    private void addMember() {
        String sql = "INSERT INTO members "
            + "(full_name, gender, phone, email, join_date, "
            + "plan_name, start_date, end_date, fee, status) "
            + "VALUES (?, ?, ?, ?, CURRENT_DATE, ?, ?, ?, ?, 'Active')";

        try {
            if (nameField.getText().trim().isEmpty()
                    || startField.getText().trim().isEmpty()
                    || endField.getText().trim().isEmpty()
                    || feeField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Fill in name, dates and fee."
                );
                return;
            }

            Date startDate = Date.valueOf(startField.getText().trim());
            Date endDate = Date.valueOf(endField.getText().trim());
            double fee = Double.parseDouble(feeField.getText().trim());

            if (fee < 0 || endDate.before(startDate)) {
                JOptionPane.showMessageDialog(
                    this, "Check the fee and membership dates."
                );
                return;
            }

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nameField.getText().trim());
                ps.setString(2, (String) genderBox.getSelectedItem());
                ps.setString(3, phoneField.getText().trim());
                ps.setString(4, emailField.getText().trim());
                ps.setString(5, (String) planBox.getSelectedItem());
                ps.setDate(6, startDate);
                ps.setDate(7, endDate);
                ps.setDouble(8, fee);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                this, "Member added successfully!"
            );
            clearFields();
            loadMembers();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                this, "Use dates like 2026-10-13 and enter a valid fee."
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Database error: " + ex.getMessage()
            );
        }
    }

    private void loadMembers() {
        model.setRowCount(0);

        String sql = "SELECT member_id, full_name, phone, email, "
            + "plan_name, start_date, end_date, fee, status "
            + "FROM members ORDER BY member_id DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("member_id"),
                    rs.getString("full_name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("plan_name"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getBigDecimal("fee"),
                    rs.getString("status")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Could not load members: " + ex.getMessage()
            );
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        startField.setText("");
        endField.setText("");
        feeField.setText("");
        genderBox.setSelectedIndex(0);
        planBox.setSelectedIndex(0);
    }
}

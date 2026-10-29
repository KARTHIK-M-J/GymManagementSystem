
package gymmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PaymentFrame extends JFrame {

    private JTextField memberIdField, amountField, dateField;
    private JTextField descriptionField;
    private JComboBox<String> methodBox;
    private DefaultTableModel model;

    public PaymentFrame() {
        setTitle("Gym Management - Payments");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(3, 4, 8, 8));
        form.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        memberIdField = new JTextField();
        amountField = new JTextField();
        dateField = new JTextField(
            java.time.LocalDate.now().toString()
        );
        descriptionField = new JTextField();

        methodBox = new JComboBox<>(
            new String[]{"Cash", "Card", "UPI", "Bank Transfer"}
        );

        form.add(new JLabel("Member ID:"));
        form.add(memberIdField);
        form.add(new JLabel("Amount:"));
        form.add(amountField);

        form.add(new JLabel("Payment Date (YYYY-MM-DD):"));
        form.add(dateField);
        form.add(new JLabel("Method:"));
        form.add(methodBox);

        form.add(new JLabel("Description:"));
        form.add(descriptionField);

        JButton addButton = new JButton("Record Payment");
        JButton refreshButton = new JButton("Refresh");

        JPanel actions = new JPanel();
        actions.add(addButton);
        actions.add(refreshButton);

        model = new DefaultTableModel(
            new String[]{
                "Payment ID", "Member ID", "Member Name",
                "Amount", "Date", "Method", "Description"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);

        addButton.addActionListener(e -> addPayment());
        refreshButton.addActionListener(e -> loadPayments());

        JPanel north = new JPanel(new BorderLayout());
        north.add(form, BorderLayout.CENTER);
        north.add(actions, BorderLayout.SOUTH);

        add(north, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadPayments();
    }

    private void addPayment() {
        String sql = "INSERT INTO payments "
            + "(member_id, amount, payment_date, payment_method, description) "
            + "VALUES (?, ?, ?, ?, ?)";

        try {
            int memberId = Integer.parseInt(
                memberIdField.getText().trim()
            );
            double amount = Double.parseDouble(
                amountField.getText().trim()
            );
            Date paymentDate = Date.valueOf(
                dateField.getText().trim()
            );

            if (memberId <= 0 || amount <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Member ID and amount must be positive."
                );
                return;
            }

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, memberId);
                ps.setBigDecimal(2, java.math.BigDecimal.valueOf(amount));
                ps.setDate(3, paymentDate);
                ps.setString(4, (String) methodBox.getSelectedItem());
                ps.setString(5, descriptionField.getText().trim());

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                this, "Payment recorded successfully!"
            );
            memberIdField.setText("");
            amountField.setText("");
            descriptionField.setText("");
            loadPayments();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Enter a valid numeric member ID and amount."
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                this, "Enter the date in YYYY-MM-DD format."
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Could not record payment: " + ex.getMessage()
            );
        }
    }

    private void loadPayments() {
        model.setRowCount(0);

        String sql = "SELECT p.payment_id, p.member_id, m.full_name, "
            + "p.amount, p.payment_date, p.payment_method, p.description "
            + "FROM payments p JOIN members m "
            + "ON p.member_id = m.member_id "
            + "ORDER BY p.payment_id DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("payment_id"),
                    rs.getInt("member_id"),
                    rs.getString("full_name"),
                    rs.getBigDecimal("amount"),
                    rs.getDate("payment_date"),
                    rs.getString("payment_method"),
                    rs.getString("description")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Could not load payments: " + ex.getMessage()
            );
        }
    }
}


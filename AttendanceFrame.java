
package gymmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AttendanceFrame extends JFrame {

    private JTextField memberIdField;
    private DefaultTableModel model;

    public AttendanceFrame() {
        setTitle("Gym Management - Attendance");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        memberIdField = new JTextField(12);
        JButton checkInButton = new JButton("Check In");
        JButton refreshButton = new JButton("Refresh");

        JPanel top = new JPanel();
        top.add(new JLabel("Member ID:"));
        top.add(memberIdField);
        top.add(checkInButton);
        top.add(refreshButton);

        model = new DefaultTableModel(
            new String[]{
                "Attendance ID", "Member ID", "Member Name",
                "Date", "Check-in Time"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);

        checkInButton.addActionListener(e -> checkIn());
        refreshButton.addActionListener(e -> loadAttendance());

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadAttendance();
    }

    private void checkIn() {
        String sql = "INSERT INTO attendance "
            + "(member_id, attendance_date, check_in) "
            + "VALUES (?, CURRENT_DATE, CURRENT_TIME)";

        try {
            int memberId = Integer.parseInt(
                memberIdField.getText().trim()
            );

            if (memberId <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Enter a positive member ID."
                );
                return;
            }

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, memberId);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                this, "Attendance recorded successfully!"
            );

            memberIdField.setText("");
            loadAttendance();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Enter a valid numeric member ID."
            );
        } catch (SQLException ex) {
            String message =
                "Could not record attendance: " + ex.getMessage();

            if (ex.getSQLState() != null
                    && ex.getSQLState().startsWith("23")) {
                message =
                    "Invalid member ID or already checked in today.";
            }

            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void loadAttendance() {
        model.setRowCount(0);

        String sql = "SELECT a.attendance_id, a.member_id, "
            + "m.full_name, a.attendance_date, a.check_in "
            + "FROM attendance a JOIN members m "
            + "ON a.member_id = m.member_id "
            + "ORDER BY a.attendance_date DESC, a.check_in DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("attendance_id"),
                    rs.getInt("member_id"),
                    rs.getString("full_name"),
                    rs.getDate("attendance_date"),
                    rs.getTime("check_in")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Could not load attendance: " + ex.getMessage()
            );
        }
    }
}

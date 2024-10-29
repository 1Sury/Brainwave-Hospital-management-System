import javax.swing.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

class StaffForm extends JFrame {
    public StaffForm() {
        setTitle("Staff Management");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblRole = new JLabel("Role:");
        JTextField txtRole = new JTextField();
        JButton btnAddStaff = new JButton("Add Staff");

        btnAddStaff.addActionListener(e -> {
            String name = txtName.getText();
            String role = txtRole.getText();

            if (addStaff(name, role)) {
                JOptionPane.showMessageDialog(this, "Staff Added Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Add Staff");
            }
        });

        add(lblName);
        add(txtName);
        add(lblRole);
        add(txtRole);
        add(btnAddStaff);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean addStaff(String name, String role) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO staff (name, role) VALUES (?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

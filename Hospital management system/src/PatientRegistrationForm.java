import javax.swing.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

class PatientRegistrationForm extends JFrame {
    public PatientRegistrationForm() {
        setTitle("Patient Registration");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblAge = new JLabel("Age:");
        JTextField txtAge = new JTextField();
        JLabel lblGender = new JLabel("Gender:");
        JTextField txtGender = new JTextField();
        JLabel lblContact = new JLabel("Contact:");
        JTextField txtContact = new JTextField();
        JLabel lblAddress = new JLabel("Address:");
        JTextField txtAddress = new JTextField();
        JButton btnRegister = new JButton("Register");

        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String gender = txtGender.getText();
            String contact = txtContact.getText();
            String address = txtAddress.getText();

            if (registerPatient(name, age, gender, contact, address)) {
                JOptionPane.showMessageDialog(this, "Patient Registered Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        });

        add(lblName);
        add(txtName);
        add(lblAge);
        add(txtAge);
        add(lblGender);
        add(txtGender);
        add(lblContact);
        add(txtContact);
        add(lblAddress);
        add(txtAddress);
        add(btnRegister);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean registerPatient(String name, int age, String gender, String contact, String address) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO patients (name, age, gender, contact, address) VALUES (?, ?, ?, ?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, contact);
            stmt.setString(5, address);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

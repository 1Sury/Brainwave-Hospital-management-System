import javax.swing.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

class BillingForm extends JFrame {
    public BillingForm() {
        setTitle("Billing and Invoicing");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JLabel lblPatientId = new JLabel("Patient ID:");
        JTextField txtPatientId = new JTextField();
        JLabel lblAmount = new JLabel("Amount:");
        JTextField txtAmount = new JTextField();
        JButton btnGenerateInvoice = new JButton("Generate Invoice");

        btnGenerateInvoice.addActionListener(e -> {
            int patientId = Integer.parseInt(txtPatientId.getText());
            double amount = Double.parseDouble(txtAmount.getText());

            if (generateInvoice(patientId, amount)) {
                JOptionPane.showMessageDialog(this, "Invoice Generated Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invoice Generation Failed");
            }
        });

        add(lblPatientId);
        add(txtPatientId);
        add(lblAmount);
        add(txtAmount);
        add(btnGenerateInvoice);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean generateInvoice(int patientId, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO billing (patient_id, amount, date) VALUES (?, ?, NOW())";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, patientId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

import javax.swing.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

class EHRForm extends JFrame {
    public EHRForm() {
        setTitle("Electronic Health Records");
        setSize(400, 300);
        setLayout(new GridLayout(3, 2));

        JLabel lblPatientId = new JLabel("Patient ID:");
        JTextField txtPatientId = new JTextField();
        JLabel lblRecord = new JLabel("Medical Record:");
        JTextArea txtRecord = new JTextArea();
        JButton btnUpdate = new JButton("Update Record");

        btnUpdate.addActionListener(e -> {
            int patientId = Integer.parseInt(txtPatientId.getText());
            String record = txtRecord.getText();

            if (updateEHR(patientId, record)) {
                JOptionPane.showMessageDialog(this, "EHR Updated Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "EHR Update Failed");
            }
        });

        add(lblPatientId);
        add(txtPatientId);
        add(lblRecord);
        add(new JScrollPane(txtRecord));
        add(btnUpdate);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean updateEHR(int patientId, String record) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE patients SET medical_history = ? WHERE id = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, record);
            stmt.setInt(2, patientId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

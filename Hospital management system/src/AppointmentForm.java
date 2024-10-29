import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;

import java.sql.Connection;


class AppointmentForm extends JFrame {
    public AppointmentForm() {
        setTitle("Schedule Appointment");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JLabel lblPatientId = new JLabel("Patient ID:");
        JTextField txtPatientId = new JTextField();
        JLabel lblDoctorId = new JLabel("Doctor ID:");
        JTextField txtDoctorId = new JTextField();
        JLabel lblDate = new JLabel("Appointment Date (YYYY-MM-DD):");
        JTextField txtDate = new JTextField();
        JButton btnSchedule = new JButton("Schedule");

        btnSchedule.addActionListener(e -> {
            int patientId = Integer.parseInt(txtPatientId.getText());
            int doctorId = Integer.parseInt(txtDoctorId.getText());
            String appointmentDate = txtDate.getText();

            if (scheduleAppointment(patientId, doctorId, appointmentDate)) {
                JOptionPane.showMessageDialog(this, "Appointment Scheduled Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Scheduling Failed");
            }
        });

        add(lblPatientId);
        add(txtPatientId);
        add(lblDoctorId);
        add(txtDoctorId);
        add(lblDate);
        add(txtDate);
        add(btnSchedule);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean scheduleAppointment(int patientId, int doctorId, String appointmentDate) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, 'Scheduled')";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setString(3, appointmentDate);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

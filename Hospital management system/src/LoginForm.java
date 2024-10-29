import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;

import java.sql.Connection;


class LoginForm extends JFrame {
    public LoginForm() {
        setTitle("Login");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setLocationRelativeTo(Object o) {
    }

    private boolean authenticate(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            var rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

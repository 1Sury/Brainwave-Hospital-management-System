import javax.swing.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.SQLException;

class InventoryForm extends JFrame {
    public InventoryForm() {
        setTitle("Inventory Management");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        JLabel lblItem = new JLabel("Item Name:");
        JTextField txtItem = new JTextField();
        JLabel lblQuantity = new JLabel("Quantity:");
        JTextField txtQuantity = new JTextField();
        JButton btnUpdateStock = new JButton("Update Stock");

        btnUpdateStock.addActionListener(e -> {
            String item = txtItem.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());

            if (updateInventory(item, quantity)) {
                JOptionPane.showMessageDialog(this, "Inventory Updated Successfully");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Inventory Update Failed");
            }
        });

        add(lblItem);
        add(txtItem);
        add(lblQuantity);
        add(txtQuantity);
        add(btnUpdateStock);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean updateInventory(String item, int quantity) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE inventory SET quantity = ? WHERE item_name = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, quantity);
            stmt.setString(2, item);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

import javax.swing.*;
import java.awt.*;

public class HospitalManagementSystem extends JFrame {
    public HospitalManagementSystem() {
        // Window setup
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menu bar setup
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

    
        JMenu loginMenu = new JMenu("Login");
        JMenu patientMenu = new JMenu("Patient");
        JMenu appointmentMenu = new JMenu("Appointment");
        JMenu ehrMenu = new JMenu("EHR");
        JMenu billingMenu = new JMenu("Billing");
        JMenu inventoryMenu = new JMenu("Inventory");
        JMenu staffMenu = new JMenu("Staff");

       
        loginMenu.setToolTipText("Login to access your account");
        patientMenu.setToolTipText("Register or manage patients");
        appointmentMenu.setToolTipText("Manage appointments");
        ehrMenu.setToolTipText("Access electronic health records");
        billingMenu.setToolTipText("Billing operations");
        inventoryMenu.setToolTipText("Manage hospital inventory");
        staffMenu.setToolTipText("Staff management");

        // Adding icons
        loginMenu.setIcon(new ImageIcon("src/images/user-interface.png"));
        patientMenu.setIcon(new ImageIcon("src/images/hospitalisation.png"));
        appointmentMenu.setIcon(new ImageIcon("src/images/advice.png"));
        ehrMenu.setIcon(new ImageIcon("src/images/medical-prescription.png"));
        billingMenu.setIcon(new ImageIcon("src/images/bill.png"));
        inventoryMenu.setIcon(new ImageIcon("src/images/inventory.png"));
        staffMenu.setIcon(new ImageIcon("src/images/medical-team.png"));

        // Add menus to the menu bar
        menuBar.add(loginMenu);
        menuBar.add(patientMenu);
        menuBar.add(appointmentMenu);
        menuBar.add(ehrMenu);
        menuBar.add(billingMenu);
        menuBar.add(inventoryMenu);
        menuBar.add(staffMenu);

        // Menu Actions
        loginMenu.add(new JMenuItem("Login")).addActionListener(e -> new LoginForm());
        patientMenu.add(new JMenuItem("Register Patient")).addActionListener(e -> new PatientRegistrationForm());
        appointmentMenu.add(new JMenuItem("Schedule Appointment")).addActionListener(e -> new AppointmentForm());
        ehrMenu.add(new JMenuItem("View EHR")).addActionListener(e -> new EHRForm());
        billingMenu.add(new JMenuItem("Billing")).addActionListener(e -> new BillingForm());
        inventoryMenu.add(new JMenuItem("Manage Inventory")).addActionListener(e -> new InventoryForm());
        staffMenu.add(new JMenuItem("Manage Staff")).addActionListener(e -> new StaffForm());

        setContentPane(new BackgroundPanel("src/images/th.jpg"));

        // Adding a welcome message or banner panel
        JLabel welcomeLabel = new JLabel("Welcome to the Hospital Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(0, 128, 128));

        // Center the welcome label in the window
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.add(welcomeLabel);

        add(labelPanel, BorderLayout.CENTER);
    }
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
           
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalManagementSystem().setVisible(true);
        });
    }
}


package view;

import javax.swing.*;

// ManagerDashboard provides access to manager-only features
public class ManagerDashboard extends JFrame {

    private JFrame parent;

    public ManagerDashboard(JFrame parent) {

        this.parent = parent;

        setTitle("Branch Manager Dashboard");
        setSize(450,250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("Glow & Go 43 - Manager Dashboard");
        lblTitle.setBounds(20,10,350,25);
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        add(lblTitle);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(320,10,90,25);
        add(btnLogout);

        JButton btnProducts = new JButton("Manage Products");
        btnProducts.setBounds(20,80,185,25);
        add(btnProducts);

        JButton btnUsers = new JButton("Create User");
        btnUsers.setBounds(225,80,185,25);
        add(btnUsers);

        JButton btnStock = new JButton("Monitor Stock & Restock Alerts");
        btnStock.setBounds(20,120,390,25);
        add(btnStock);

        // Navigation actions
        btnProducts.addActionListener(e -> {
            setVisible(false);
            new ProductView(this);
        });

        btnUsers.addActionListener(e -> {
            setVisible(false);
            new CreateUserView(this);
        });

        btnStock.addActionListener(e -> {
            setVisible(false);
            new MonitorStockView(this);
        });

        btnLogout.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}




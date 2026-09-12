
package view;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/*
 * This class allows the manager to:
 * - View all products
 * - Detect low stock items
 * - Receive restock alerts
 * It is mainly a VIEW with minimal business logic
 */
public class MonitorStockView extends JFrame {

    JTable tbl;
    DefaultTableModel model;

    // DAO used to retrieve product data
    ProductDAO dao = new ProductDAO();

    // Reference to previous screen
    JFrame parent;

    // Constructor initializes UI and loads data
    public MonitorStockView(JFrame parent) {

        this.parent = parent;

        setTitle("Monitor Stock - Glow & Go 43");
        setSize(650,400);
        setLayout(null);
        setLocationRelativeTo(null);

        // TITLE LABEL
        JLabel lblTitle = new JLabel("Glow & Go 43 - Stock Monitoring");
        lblTitle.setBounds(200,10,300,25);
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        add(lblTitle);

        // PRODUCT TABLE
        model = new DefaultTableModel(
            new String[]{"ID","Name","Category","Price","Quantity"}, 0
        );
        tbl = new JTable(model);

        JScrollPane sp = new JScrollPane(tbl);
        sp.setBounds(20,50,600,230);
        add(sp);

        // ACTION BUTTONS
        JButton btnLowStock = new JButton("Check Low Stock");
        btnLowStock.setBounds(20,300,150,25);
        add(btnLowStock);

        JButton btnLoadAll = new JButton("Load All");
        btnLoadAll.setBounds(190,300,120,25);
        add(btnLoadAll);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(520,300,100,25);
        add(btnBack);
        
        // RESTOCK ALERT ON LOAD
        // Automatically shows alert when screen opens
        showRestockAlert();

        // Load all products initially
        loadAllProducts();

        // BUTTON ACTIONS
        btnLowStock.addActionListener(e -> loadLowStock());
        btnLoadAll.addActionListener(e -> loadAllProducts());

        btnBack.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });

        setVisible(true);
    }

    /*
     * Shows a warning message for products
     * that are below the reorder level
     */
    private void showRestockAlert() {
        try {
            StringBuilder alertMsg = new StringBuilder();
            int count = 0;

            // Check each product's quantity
            for (Product p : new ProductDAO().getAll()) {
                if (p.getQty() <= p.getReorderLevel()) {
                    count++;
                    alertMsg.append("- ")
                            .append(p.getName())
                            .append(" (Qty: ")
                            .append(p.getQty())
                            .append(")\n");
                }
            }

            // Show alert only if low stock items exist
            if (count > 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "⚠ RESTOCK ALERT ⚠\n\n" +
                    "The following products need restocking:\n\n" +
                    alertMsg.toString(),
                    "Restock Alert",
                    JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Loads all products into the table
    private void loadAllProducts() {
        try {
            model.setRowCount(0);
            List<Product> list = dao.getAll();

            for (Product p : list) {
                model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getCategoryId(),
                    p.getPrice(),
                    p.getQty()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading products");
        }
    }

    // Loads only products with low stock
    private void loadLowStock() {
        try {
            model.setRowCount(0);
            List<Product> list = dao.getAll();

            boolean found = false;

            // Business rule: quantity <= 10 is low stock
            for (Product p : list) {
                if (p.getQty() <= 10) {
                    model.addRow(new Object[]{
                        p.getId(),
                        p.getName(),
                        p.getCategoryId(),
                        p.getPrice(),
                        p.getQty()
                    });
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this,
                    "No low stock items found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error checking low stock");
        }
    }
}




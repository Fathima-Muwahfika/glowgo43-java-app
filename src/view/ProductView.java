
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ProductController;
import dao.CategoryDAO;
import model.Category;


/*
 * This class represents the PRODUCT MANAGEMENT UI.
 * - Displays product form and product table
 * - Does NOT contain database logic
 * - Delegates actions to ProductController
 */
public class ProductView extends JFrame {

    // UI components accessed by the controller
    public JTextField txtName, txtPrice, txtQty, txtSearch;
    public JComboBox<String> cmbCategory;
    public JButton btnAdd, btnSearch, btnLoad, btnClear;
    public JTable tblProducts;
    public DefaultTableModel model;

    // Reference to parent frame (Manager Dashboard)
    private JFrame parent;

    // Constructor initializes the UI
    public ProductView(JFrame parent) {

        this.parent = parent;

        setTitle("Product Management");
        setSize(650,450);
        setLayout(null);
        setLocationRelativeTo(null);

        // TITLE LABEL
        JLabel lblTitle = new JLabel("Glow & Go 43 - Product Management");
        lblTitle.setBounds(190,10,350,25);
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        add(lblTitle);

        // BACK BUTTON
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(540,10,80,25);
        add(btnBack);

        // Navigates back to the previous dashboard
        btnBack.addActionListener(e -> {
            dispose();
            parent.setVisible(true);   
        });

        // PRODUCT INPUT FORM
        add(new JLabel("Name")).setBounds(20,50,80,25);
        add(new JLabel("Category")).setBounds(20,85,80,25);
        add(new JLabel("Price")).setBounds(20,120,80,25);
        add(new JLabel("Quantity")).setBounds(20,155,80,25);

        txtName = new JTextField();
        txtName.setBounds(100,50,150,25);
        add(txtName);

        cmbCategory = new JComboBox<>();
        loadCategories(); // load from DB
        cmbCategory.addItem("Other"); // last option
        cmbCategory.setBounds(100,85,150,25);
        add(cmbCategory);
        
        cmbCategory.addActionListener(e -> {

            if (cmbCategory.getSelectedItem().equals("Other")) {

                String newCategory = JOptionPane.showInputDialog(
                        this,
                        "Enter new category name:"
                );

                if (newCategory != null && !newCategory.trim().isEmpty()) {
                    try {
                        CategoryDAO dao = new CategoryDAO();

                        if (!dao.categoryExists(newCategory)) {
                            dao.addCategory(newCategory);

                            cmbCategory.insertItemAt(newCategory, 
                                    cmbCategory.getItemCount() - 1);

                            cmbCategory.setSelectedItem(newCategory);

                            JOptionPane.showMessageDialog(this,
                                    "New category added");
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Category already exists");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                "Error adding category");
                    }
                } else {
                    cmbCategory.setSelectedIndex(0);
                }
            }
        });



        txtPrice = new JTextField();
        txtPrice.setBounds(100,120,150,25);
        add(txtPrice);

        txtQty = new JTextField();
        txtQty.setBounds(100,155,150,25);
        add(txtQty);

        // FORM ACTION BUTTONS
        btnAdd = new JButton("Add");
        btnAdd.setBounds(280,85,90,25);
        add(btnAdd);

        btnClear = new JButton("Clear");
        btnClear.setBounds(280,120,90,25);
        add(btnClear);

        // TABLE MODEL
        // DefaultTableModel separates data from UI
        model = new DefaultTableModel(
            new String[]{"ID","Name","Category","Price","Quantity"},0
        );

        tblProducts = new JTable(model);
        JScrollPane sp = new JScrollPane(tblProducts);
        sp.setBounds(20,200,600,150);
        add(sp);

        // SEARCH SECTION
        add(new JLabel("Search by Name")).setBounds(20,370,120,25);

        txtSearch = new JTextField();
        txtSearch.setBounds(120,370,150,25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(280,370,90,25);
        add(btnSearch);

        btnLoad = new JButton("Load All");
        btnLoad.setBounds(460,370,120,25);
        add(btnLoad);
        
        

        // Controller handles all business logic
        new ProductController(this); 

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // Loads categories from database into combo box
    private void loadCategories() {
        try {
            CategoryDAO dao = new CategoryDAO();
            for (Category c : dao.getAllCategories()) {
                cmbCategory.addItem(c.getName());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading categories");
        }
    }

}




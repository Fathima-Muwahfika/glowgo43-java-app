package controller;

import dao.ProductDAO;
import dao.CategoryDAO;
import model.Product;
import view.ProductView;

import javax.swing.*;

public class ProductController {

    ProductDAO productDAO = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    public ProductController(ProductView v) {
        
        // PRICE – allow only numbers + decimal
v.txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        char c = e.getKeyChar();

        if (!Character.isDigit(c) && c != '.' && c != '\b') {
            e.consume(); // block character
            JOptionPane.showMessageDialog(
                    v,
                    "Input valid details",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
});

// QUANTITY – allow only numbers
v.txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); // block character
            JOptionPane.showMessageDialog(
                    v,
                    "Input valid details",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
});


        // ADD PRODUCT
        v.btnAdd.addActionListener(e -> {
            try {
                String name = v.txtName.getText().trim();
                String categoryName = v.cmbCategory.getSelectedItem().toString().trim();

                if (categoryName.equalsIgnoreCase("Other")) {
                    JOptionPane.showMessageDialog(
                            v, "Please select or create a valid category",
                            "Category Error", JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (name.isEmpty() || v.txtPrice.getText().isEmpty() || v.txtQty.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(v, "Insert valid details");
                    return;
                }

                double price = Double.parseDouble(v.txtPrice.getText());
                int qty = Integer.parseInt(v.txtQty.getText());

                if (price <= 0 || qty <= 0) {
                    JOptionPane.showMessageDialog(v, "Insert valid details");
                    return;
                }

                // Get category ID
                int categoryId = categoryDAO.getCategoryIdByName(categoryName);

                // Check duplicate
                Product existing = productDAO.findDuplicate(name, categoryId, price);

                if (existing != null) {
                    productDAO.updateQty(existing.getId(), existing.getQty() + qty);
                    JOptionPane.showMessageDialog(v, "Product already exists. Quantity updated.");
                } else {
                    Product p = new Product();
                    p.setName(name);
                    p.setCategoryId(categoryId);
                    p.setPrice(price);
                    p.setQty(qty);
                    productDAO.add(p);
                    JOptionPane.showMessageDialog(v, "Product added successfully");
                }

                v.btnLoad.doClick();
                v.btnClear.doClick();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(v, ex.getMessage());
            }
        });

        // SEARCH PRODUCT
        v.btnSearch.addActionListener(e -> {
            try {
                v.model.setRowCount(0);
                for (Product p : productDAO.search(v.txtSearch.getText())) {
                    String categoryName = categoryDAO.getCategoryNameById(p.getCategoryId());
                    v.model.addRow(new Object[]{
                            p.getId(),
                            p.getName(),
                            categoryName,
                            p.getPrice(),
                            p.getQty()
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // LOAD ALL PRODUCTS
        v.btnLoad.addActionListener(e -> {
            try {
                v.model.setRowCount(0);
                for (Product p : productDAO.getAll()) {
                    String categoryName = categoryDAO.getCategoryNameById(p.getCategoryId());
                    v.model.addRow(new Object[]{
                            p.getId(),
                            p.getName(),
                            categoryName,
                            p.getPrice(),
                            p.getQty()
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // CLEAR FIELDS
        v.btnClear.addActionListener(e -> {
            v.txtName.setText("");
            v.txtPrice.setText("");
            v.txtQty.setText("");
            v.txtSearch.setText("");
        });
    }
}

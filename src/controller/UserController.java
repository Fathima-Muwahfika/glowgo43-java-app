
package controller;

import view.CreateUserView;
import dao.UserDAO;

import javax.swing.*;
import java.sql.ResultSet;


 // Handles user-related logic
 // Connects CreateUserView and UserDAO
 // Contains validation and decision-making
 
public class UserController {

    private CreateUserView view;
    private UserDAO dao;

    // Constructor connects the view with controller
    public UserController(CreateUserView view) {
        this.view = view;
        this.dao = new UserDAO();

        // Register button actions
        view.btnCreate.addActionListener(e -> createUser());
        view.btnLoad.addActionListener(e -> loadUsers());
        view.btnClear.addActionListener(e -> view.clearFields());
    }

    // Creates a new user account
    // Business logic and validation are handled here
    private void createUser() {
        try {
            String username = view.getUsername();
            String password = view.getPassword();
            String role = view.getRole();

            // Validation logic
            if (username.isEmpty() || password.isEmpty()) {
                view.showMessage(
                        "Please enter username and password",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (password.length() < 4) {
                view.showMessage(
                        "Password must be at least 4 characters",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // DAO handles database insertion
            dao.createUser(username, password, role);

            view.showMessage(
                    "User created successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Clear form after successful creation
            view.clearFields();

        } catch (Exception ex) {
            view.showMessage(
                    "Error creating user",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    // Loads all users from database into the table
    private void loadUsers() {
        try {
            // Clear existing table rows
            view.model.setRowCount(0);

            // DAO retrieves data from database
            ResultSet rs = dao.getAllUsers();

            // Each row is added to the table model
            while (rs.next()) {
                view.model.addRow(new Object[]{
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role")
                });
            }

        } catch (Exception ex) {
            view.showMessage(
                    "Error loading users",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }
}


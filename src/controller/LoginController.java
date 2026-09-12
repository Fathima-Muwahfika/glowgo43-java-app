/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.User;
import view.*;

import javax.swing.JOptionPane;

// LoginController handles all login related actions
// It acts as the Controller in MVC architecture
public class LoginController {

    public LoginController(LoginFrame v) {

        // ActionListener handles the Login button click
        v.btnLogin.addActionListener(e -> {

            try {
                // Get selected role from ComboBox
                String role =
                    v.cmbRole.getSelectedItem().toString().toLowerCase();

                // Read user input from the view
                String username = v.txtUser.getText().trim();
                String password =
                    String.valueOf(v.txtPass.getPassword());

                // Basic validation to avoid empty input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        v, "Please enter username and password");
                    return;
                }

                // DAO object is used to communicate with database
                UserDAO dao = new UserDAO();

                // Polymorphism: login() returns a User object
                // which can be BranchManager or BeautyAssistant
                User u = dao.login(role, username, password);

                // If login fails, user object will be null
                if (u == null) {
                    JOptionPane.showMessageDialog(
                        v, "Invalid username, password, or role");
                    return;
                }

                // Hide login window after successful login
                v.setVisible(false);

                // Role-based navigation (runtime decision)
                // Demonstrates polymorphism and abstraction
                if (u.getRole().equalsIgnoreCase("manager")) {
                    new ManagerDashboard(v);
                }
                else if (u.getRole().equalsIgnoreCase("assistant")) {
                    new ProductView(v);
                }

            } catch (Exception ex) {
                // Handles database or system errors safely
                JOptionPane.showMessageDialog(
                    v, "Database error");
            }
        });
    }
}




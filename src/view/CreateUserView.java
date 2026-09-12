
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.UserController;

public class CreateUserView extends JFrame {

    // UI COMPONENTS (PUBLIC for Controller access)
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JComboBox<String> cmbRole;
    public JTable tblUsers;
    public DefaultTableModel model;

    public JButton btnCreate;
    public JButton btnClear;
    public JButton btnLoad;
    public JButton btnBack;

    private JFrame parent;

    public CreateUserView(JFrame parent) {

        this.parent = parent;

        setTitle("Glow & Go 43 - Create User");
        setSize(520, 430);
        setLayout(null);
        setLocationRelativeTo(null);

        // TITLE
        JLabel lblTitle = new JLabel("Glow & Go 43 - Create User Account", SwingConstants.CENTER);
        lblTitle.setBounds(100, 10, 320, 25);
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        add(lblTitle);

        // FORM LABELS
        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(40, 55, 80, 25);
        add(lblRole);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(40, 90, 80, 25);
        add(lblUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(40, 125, 80, 25);
        add(lblPass);

        // FORM FIELDS
        cmbRole = new JComboBox<>(new String[]{"Assistant", "Manager"});
        cmbRole.setBounds(150, 55, 160, 25);
        add(cmbRole);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 90, 160, 25);
        add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 125, 160, 25);
        add(txtPassword);

        // BUTTONS
        btnBack = new JButton("Back");
        btnBack.setBounds(410, 10, 80, 25);
        add(btnBack);

        btnCreate = new JButton("Create");
        btnCreate.setBounds(330, 70, 120, 25);
        add(btnCreate);

        btnClear = new JButton("Clear");
        btnClear.setBounds(330, 105, 120, 25);
        add(btnClear);

        // USER TABLE
        model = new DefaultTableModel(
                new String[]{"Username", "Password", "Role"}, 0
        );

        tblUsers = new JTable(model);
        JScrollPane sp = new JScrollPane(tblUsers);
        sp.setBounds(30, 180, 460, 170);
        add(sp);

        btnLoad = new JButton("Load All Users");
        btnLoad.setBounds(180, 360, 160, 25);
        add(btnLoad);

        // BASIC VIEW ACTIONS
        btnBack.addActionListener(e -> {
            dispose();
            parent.setVisible(true);
        });

        btnClear.addActionListener(e -> clearFields());
        
        new UserController(this);


        setVisible(true);
    }

    // HELPER METHODS FOR CONTROLLER

    // Get entered username
    public String getUsername() {
        return txtUsername.getText().trim();
    }

    // Get entered password
    public String getPassword() {
        return String.valueOf(txtPassword.getPassword());
    }

    // Get selected role
    public String getRole() {
        return cmbRole.getSelectedItem().toString();
    }

    // Clear all input fields
    public void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
    }

    // Show message dialog (success / error / warning)
    public void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}





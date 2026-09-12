
package view;

import javax.swing.*;
import controller.LoginController;

// LoginFrame is the entry point of the system
// This class handles user input for login
public class LoginFrame extends JFrame {

    public JComboBox<String> cmbRole;
    public JTextField txtUser;
    public JPasswordField txtPass;
    public JButton btnLogin;
    public JCheckBox chkShowPass;

    public LoginFrame() {

        setTitle("Glow & Go 43 - Login");
        setSize(360,300);
        setLayout(null);
        setLocationRelativeTo(null);

        // TITLE
        JLabel lblTitle = new JLabel("Glow & Go 43");
        lblTitle.setBounds(118,15,200,25);
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        add(lblTitle);

        // LABELS
        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(40,60,80,25);
        add(lblRole);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(40,100,80,25);
        add(lblUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(40,140,80,25);
        add(lblPass);

        // INPUT FIELDS
        cmbRole = new JComboBox<>(new String[]{"Assistant", "Manager"});
        cmbRole.setBounds(140,60,160,25);
        add(cmbRole);

        txtUser = new JTextField();
        txtUser.setBounds(140,100,160,25);
        add(txtUser);

        txtPass = new JPasswordField();
        txtPass.setBounds(140,140,160,25);
        add(txtPass);

        // SHOW PASSWORD
        chkShowPass = new JCheckBox("Show Password");
        chkShowPass.setBounds(185,165,160,20);
        chkShowPass.setFont(chkShowPass.getFont().deriveFont(11f));
        add(chkShowPass);

        chkShowPass.addActionListener(e -> {
            if (chkShowPass.isSelected())
                txtPass.setEchoChar((char) 0);
            else
                txtPass.setEchoChar('•');
        });

        // LOGIN BUTTON
        btnLogin = new JButton("Login");
        btnLogin.setBounds(110,205,120,25);
        add(btnLogin);

        // Controller handles login logic
        new LoginController(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}




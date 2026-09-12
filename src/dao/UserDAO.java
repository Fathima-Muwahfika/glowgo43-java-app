
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import model.BranchManager;
import model.BeautyAssistant;
import util.DBConnection;

// UserDAO handles all database operations related to users
public class UserDAO {

    // Login method checks user credentials and role
    // Returns a User object if login is successful
    public User login(String role, String username, String password)
            throws Exception {

        String sql =
            "SELECT * FROM users WHERE role=? AND username=? AND password_hash=?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, role.toLowerCase());
        ps.setString(2, username);
        ps.setString(3, password);

        ResultSet rs = ps.executeQuery();

        // If user exists, create appropriate User object
        if (rs.next()) {

            int id = rs.getInt("user_id");
            String uname = rs.getString("username");

            // Polymorphism: User reference can hold subclass objects
            if (role.equalsIgnoreCase("manager")) {
                return new BranchManager(id, uname);
            } else {
                return new BeautyAssistant(id, uname);
            }
        }
        return null;
    }

    // Creates a new user account (Manager only)
    public void createUser(String username, String password, String role)
            throws Exception {

        String sql =
            "INSERT INTO users (username, password_hash, role) VALUES (?,?,?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, role.toLowerCase());

        ps.executeUpdate();
    }

    // Loads all users from the database
    public ResultSet getAllUsers() throws Exception {
        String sql = "SELECT username, password_hash, role FROM users";
        return DBConnection.getConnection()
                .createStatement()
                .executeQuery(sql);
    }
}



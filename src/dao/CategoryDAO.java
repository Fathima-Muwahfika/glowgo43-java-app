package dao;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO {

    // Add new category to database
    public void addCategory(String name) throws Exception {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    // Check if category already exists
    public boolean categoryExists(String name) throws Exception {
        String sql = "SELECT * FROM categories WHERE name=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    // Load all categories
    public List<Category> getAllCategories() throws Exception {
        List<Category> list = new ArrayList<>();
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM categories");

        while (rs.next()) {
            list.add(new Category(
                    rs.getInt("id"),
                    rs.getString("name")
            ));
        }
        return list;
    }

    // Get category ID by name
    public int getCategoryIdByName(String name) throws Exception {
        String sql = "SELECT id FROM categories WHERE name=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        throw new Exception("Category not found");
    }

    // Get category name by ID
    public String getCategoryNameById(int id) throws Exception {
        String sql = "SELECT name FROM categories WHERE id=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("name");
        }
        throw new Exception("Category not found");
    }
}

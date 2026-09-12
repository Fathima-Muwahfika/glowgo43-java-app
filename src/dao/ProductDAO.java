package dao;

import model.Product;
import java.sql.*;
import java.util.*;
import util.DBConnection;

public class ProductDAO {

    // Add new product
    public void add(Product p) throws Exception {
        String sql = "INSERT INTO products(name, category_id, price, qty) VALUES(?,?,?,?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setInt(2, p.getCategoryId());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getQty());
        ps.executeUpdate();
    }

    // Find duplicate product
    public Product findDuplicate(String name, int categoryId, double price) throws Exception {
        String sql = "SELECT * FROM products WHERE name=? AND category_id=? AND price=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, categoryId);
        ps.setDouble(3, price);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setQty(rs.getInt("qty"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getDouble("price"));
            return p;
        }
        return null;
    }

    // Update quantity
    public void updateQty(int id, int qty) throws Exception {
        String sql = "UPDATE products SET qty=? WHERE id=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, qty);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    // Get all products
    public List<Product> getAll() throws Exception {
        List<Product> list = new ArrayList<>();
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");

        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setPrice(rs.getDouble("price"));
            p.setQty(rs.getInt("qty"));
            list.add(p);
        }
        return list;
    }

    // Search products by name
    public List<Product> search(String key) throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, "%" + key + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setPrice(rs.getDouble("price"));
            p.setQty(rs.getInt("qty"));
            list.add(p);
        }
        return list;
    }
}

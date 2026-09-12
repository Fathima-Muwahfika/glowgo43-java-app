package model;

// Product class represents product entity
// Demonstrates encapsulation using private variables and getters/setters
public class Product {

    private int id;
    private String name;
    private int categoryId;       // DB column
    private String categoryName;  // for display in table
    private int qty;
    private double price;

    // Constant reorder level (cannot be changed)
    private final int reorderLevel = 10;

    public Product(){   
    }

    public Product(String name, String categoryName, int categoryId, int qty, double price){
        this.name = name;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.qty = qty;
        this.price = price;
    }

    public int getId() { 
        return id;
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name;
    }
    public void setName(String name) { 
        this.name = name;
    }

    public int getCategoryId() { 
        return categoryId;
    }
    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId;
    }

    public String getCategoryName() { 
        return categoryName;
    }
    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName;
    }

    public int getQty() { 
        return qty;
    }
    public void setQty(int qty) { 
        this.qty = qty;
    }

    public double getPrice() { 
        return price;
    }
    public void setPrice(double price) { 
        this.price = price;
    }

    // Used for restock alert feature
    public int getReorderLevel() { 
        return reorderLevel;
    }
}

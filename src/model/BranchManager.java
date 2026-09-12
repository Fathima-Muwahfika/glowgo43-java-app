
package model;

// BranchManager is a subclass of User
// Demonstrates inheritance and role-based access
public class BranchManager extends User {

    public BranchManager() {
        super();
        this.role = "manager";
    }

    public BranchManager(int id, String username) {
        super(id, username, "manager");
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("Branch Manager Dashboard for: " + username);
    }
    
}




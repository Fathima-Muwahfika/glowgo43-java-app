
package model;

// BeautyAssistant is a subclass of User
// Demonstrates inheritance
public class BeautyAssistant extends User {

    public BeautyAssistant() {
        super();
        this.role = "assistant";
    }

    public BeautyAssistant(int id, String username) {
        super(id, username, "assistant");
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("Beauty Assistant Dashboard for: " + username);
    }
}





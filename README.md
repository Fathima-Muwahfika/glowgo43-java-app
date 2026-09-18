# Glow & Go 43 – Beauty Shop Inventory Management System

A Java Swing desktop application for managing product inventory at a beauty shop, built using the MVC architecture pattern.

## About the Project

Glow & Go 43 is a role-based inventory management system that lets a Branch Manager and Beauty Assistant log in to their own dashboards and manage products, stock levels, and user accounts. The project was built to apply core Object-Oriented Programming principles in a real, working desktop application.

## OOP Concepts Applied

- **Abstraction & Inheritance** – An abstract `User` class is extended by `BranchManager` and `BeautyAssistant`, each implementing its own `displayDashboard()` behavior
- **Polymorphism** – Login routes the user to a different dashboard at runtime depending on which `User` subclass is returned
- **Encapsulation** – `Product` and `Category` models use private fields with controlled access via getters/setters
- **Aggregation** – `Category` is a related entity referenced by `Product`

## Features

### Branch Manager
- Manage Products – add products with input-validated fields (numeric-only price/quantity)
- Create User – create new Beauty Assistant / Manager accounts
- Monitor Stock & Restock Alerts – view all products in a table and get flagged when stock falls below the reorder level

### Beauty Assistant
- View and manage the product catalog

### Authentication
- Role-based login screen (Manager / Assistant) that authenticates against the database and routes to the correct dashboard

## Tech Stack

- **Language:** Java
- **UI:** Java Swing
- **Architecture:** MVC (Model–View–Controller) with a dedicated DAO layer
- **Database:** MySQL (via JDBC)
- **IDE:** NetBeans

## Project Structure

```
src/
├── Main.java                  # Entry point — launches the Login UI
├── model/                     # Domain models
│   ├── User.java               # Abstract base class
│   ├── BranchManager.java      # User subclass
│   ├── BeautyAssistant.java    # User subclass
│   ├── Product.java
│   └── Category.java
├── view/                      # Swing UI screens
│   ├── LoginFrame.java
│   ├── ManagerDashboard.java
│   ├── ProductView.java
│   ├── CreateUserView.java
│   └── MonitorStockView.java
├── controller/                # Business logic, connects view <-> DAO
│   ├── LoginController.java
│   ├── ProductController.java
│   └── UserController.java
├── dao/                       # Database access layer
│   ├── UserDAO.java
│   ├── ProductDAO.java
│   └── CategoryDAO.java
└── util/
    └── DBConnection.java       # JDBC connection setup
```

## Getting Started

### Prerequisites
- Java JDK 8 or higher
- NetBeans IDE (recommended, since the project includes NetBeans project files)
- MySQL Server
- MySQL Connector/J (JDBC driver)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Fathima-Muwahfika/glowgo43-java-app.git
   ```
2. Open the project in NetBeans (`File > Open Project`, select the cloned folder).
3. Create a MySQL database and update the connection details in `util/DBConnection.java` (host, database name, username, password).
4. Create the required tables for users, products, and categories matching the fields used in the model classes.
5. Run `Main.java` to launch the application (Login screen).

## Author

Fathima Muwahfika — HNDIT, SLIATE Kandy

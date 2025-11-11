# 🏬 Inventory Management System (Java)

A simple **Object-Oriented Programming (OOP)** project built in **Java** for course **BCS202 – Introduction to Computing Science II**.  
This project demonstrates core OOP principles such as **encapsulation**, **inheritance**, and **method overriding** through the management of a small inventory system.

---

## 🧩 Project Overview

The system allows basic inventory operations:
- ➕ **Add** a new product  
- ✏️ **Update** existing product details  
- ❌ **Delete** products  
- 🔍 **Search** for a specific product by ID  
- 📋 **View** all products  

It also introduces the concept of **inheritance** with a specialized `PerishableProduct` class that extends the base `Product` class.

---

## 🧠 Object-Oriented Concepts Used

| Concept | Implementation |
|----------|----------------|
| **Encapsulation** | Private fields with public getters/setters in `Product` |
| **Inheritance** | `PerishableProduct` extends `Product` |
| **Method Overriding** | `displayProductInfo()` overridden in `PerishableProduct` |
| **Abstraction** | Separate classes for logical organization |
| **Polymorphism (Phase 2+)** | Both `Product` and `PerishableProduct` can be stored in one array of type `Product[]` |

---

## 🏗️ Class Structure

LabProject.Main
│

├── Product.java
│ └── Defines product properties and display method
│

├── PerishableProduct.java
│ └── Extends Product, adds expiry date, overrides display method
│

├── InventoryManager.java
│ └── Handles add, view, update, delete, and search operations
│

└── MainApp.java
└── Entry point demonstrating system functionality

## 💻 Sample Console Output

====== Inventory Management System ======
1. Add Product
2. View All Products
3. Update Product
4. Delete Product
5. Search Product by ID
6. Exit
   
Enter choice (1-6): 1

Enter A (Product) OR B (Perishable Product): a

Enter product ID: 2024

Enter product name: Laptop

Enter product price: 2999.99

Enter product quantity: 20

Product added successfully!


## 🪶 Features Implemented (Phase 1)

- ✅ Product creation using constructors  
- ✅ Displaying product information  
- ✅ Subclass (`PerishableProduct`) with overridden methods  
- ✅ Inventory array-based storage  
- ✅ Console output for adding and displaying one product  

---

## 🛠️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Inventory-Management-System.git
2. Open the project in your preferred IDE
  (e.g., IntelliJ IDEA, Eclipse, or NetBeans).

3. Run the program

4. Navigate to MainApp.java

5. Click Run ▶️ to execute

6. View the console output
   You should see the product being added and displayed successfully.



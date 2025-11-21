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

1- Clone the repository: (This command downloads the code):
   git clone https://github.com/Mo-code3/Lab-Project.git
   

2- Navigate into the directory:
   cd Lab-Project

3- Compile the code:
   javac LabProject/GUI/*.java LabProject/Main/*.java

4- Run the application:
   java LabProject.GUI.MainFrame

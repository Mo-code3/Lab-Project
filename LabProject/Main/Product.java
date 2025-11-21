package LabProject.Main;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    
    // Constructor (parameterized)
    public Product(int productId, String productName, double price, int quantity) {
        setProductID(productId);
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
    }

    // Default constructor
    public Product() {}

    // Display product info
    public void displayProductInfo() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + price);
        System.out.println("Product Quantity: " + quantity);
    }

    // Getters and Setters with validation
    public int getProductID() {
        return productId;
    }

    public void setProductID(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0!");
        }
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        this.productName = productName.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
    }
}
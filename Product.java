package LabProject.Main;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    public void displayProductInfo() { //method to display product info
        System.out.println("Product Id: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + price);
        System.out.println("Product Quantity: " + quantity);
    }

    public int getProductID() {
        return productId;
    }

    public void setProductID(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}




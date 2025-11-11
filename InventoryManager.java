package LabProject.Main;

public class InventoryManager {
    private Product[] products = new Product[50]; // Array to store Product objects

    // Add product
    public void addProduct(Product p) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) { // find empty slot
                products[i] = p;
                System.out.println("Product added successfully!");
                return;
            }
        }
        System.out.println("Inventory full! Cannot add more products.");
    }

    // View all products
    public void view() {
        System.out.println("\nAll Products:");
        for (Product p : products) {
            if (p != null) {
                System.out.println("--------------------");
                p.displayProductInfo();
                System.out.println("--------------------");
            }
        }
    }

    // Update product by ID
    public void update(int id, String newName, double newPrice, int newQuantity) {
        for (Product p : products) {
            if (p != null && p.getProductID() == id) {
                p.setProductName(newName);
                p.setPrice(newPrice);
                p.setQuantity(newQuantity);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product ID not found!");
    }

    // Delete product by ID
    public void delete(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getProductID() == id) {
                products[i] = null;
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product ID not found!");
    }

    // Search product by ID
    public void searchProduct(int id) {
        for (Product p : products) {
            if (p != null && p.getProductID() == id) {
                System.out.println("\nProduct found:");
                p.displayProductInfo();
                return;
            }
        }
        System.out.println("Product ID not found!");
    }
}

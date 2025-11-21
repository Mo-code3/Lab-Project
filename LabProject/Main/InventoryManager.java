package LabProject.Main;

public class InventoryManager {
    private Product[] products = new Product[50];

    // Add product with duplicate check
    public void addProduct(Product p) throws InventoryFull, DuplicateProductException {
        // Check for duplicate ID
        for (Product product : products) {
            if (product != null && product.getProductID() == p.getProductID()) {
                throw new DuplicateProductException("Product ID " + p.getProductID() + " already exists!");
            }
        }
        
        // Add product to first available slot
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = p;
                System.out.println("Product added successfully!");
                return;
            }
        }
        throw new InventoryFull("Inventory full! Cannot add more products.");
    }

    // View all products
    public void view() {
        System.out.println("\n========== All Products ==========");
        boolean found = false;
        for (Product p : products) {
            if (p != null) {
                found = true;
                System.out.println("----------------------------------");
                p.displayProductInfo();
                System.out.println("----------------------------------");
            }
        }
        if (!found) {
            System.out.println("No products in inventory.");
        }
        System.out.println("==================================\n");
    }

    // Update product by ID
    public void update(int id, String newName, double newPrice, int newQuantity) throws ProductNotFound {
        for (Product p : products) {
            if (p != null && p.getProductID() == id) {
                p.setProductName(newName);
                p.setPrice(newPrice);
                p.setQuantity(newQuantity);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        throw new ProductNotFound("Product ID " + id + " not found!");
    }

    // Delete product by ID
    public void delete(int id) throws ProductNotFound {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getProductID() == id) {
                products[i] = null;
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        throw new ProductNotFound("Product ID " + id + " not found!");
    }

    // Search product by ID
    public void searchProduct(int id) throws ProductNotFound {
        for (Product p : products) {
            if (p != null && p.getProductID() == id) {
                System.out.println("\n========== Product Found ==========");
                p.displayProductInfo();
                System.out.println("===================================\n");
                return;
            }
        }
        throw new ProductNotFound("Product ID " + id + " not found!");
    }
    public Product[] getAllProducts() {
        return products;
    }
}
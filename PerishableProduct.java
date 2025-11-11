package LabProject.Main;

public class PerishableProduct extends Product {
    private String expiryDate;

    public PerishableProduct(int productId, String productName, double price, int quantity, String expiryDate) {
        super(productId, productName, price, quantity);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate; 
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Overriding
    public void displayProductInfo() {
        super.displayProductInfo();
        System.out.println("Expiry Date: " + expiryDate);
    }
}

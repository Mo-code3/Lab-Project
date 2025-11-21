package LabProject.Main;

public class PerishableProduct extends Product {
    private String expiryDate;

    public PerishableProduct(int productId, String productName, double price, int quantity, String expiryDate) {
        super(productId, productName, price, quantity);
        setExpiryDate(expiryDate);
    }

    public String getExpiryDate() {
        return expiryDate; 
    }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be empty!");
        }
        
        // Validate date format MM/DD/YYYY
        if (!expiryDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Invalid date format! Use MM/DD/YYYY (e.g., 12/31/2025)");
        }
        
        // Basic validation of month and day ranges
        String[] parts = expiryDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 01 and 12!");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 01 and 31!");
        }
        
        this.expiryDate = expiryDate;
    }

    // Overriding
    public void displayProductInfo() {
        super.displayProductInfo();
        System.out.println("Expiry Date: " + expiryDate);
    }
}
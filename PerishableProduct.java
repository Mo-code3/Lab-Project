package LabProject.Main;

public class PerishableProduct extends Product {
    
    private String expiryDate;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void displayProductInfo() {
        super.displayProductInfo(); // show base info
        System.out.println("Expiry Date: " + expiryDate);
    }
}


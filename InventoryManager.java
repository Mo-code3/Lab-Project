package LabProject.Main;


public class InventoryManager {
    Object[][] arr = new Object[50][4];

    public void addProduct(int id, String name, double price, int quantity) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == null) { // find empty slot
                arr[i][0] = id;
                arr[i][1] = name;
                arr[i][2] = price;
                arr[i][3] = quantity;
                System.out.println("Product added successfully.");
                break; // stop after adding one product
            }
        }
        System.out.println("Inventory full! Cannot add more products.");
    }

    public void view(int id){
        for(int i=0; i<arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }

    public void update(int id, String newName, double newPrice, int newQuantity) {
        arr[id][1] = newName;
        arr[id][2] = newPrice;
        arr[id][3] = newQuantity;    
    }

    public void delete() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != null) {
                arr[i][0] = null;
                arr[i][1] = null;
                arr[i][2] = null;
                arr[i][3] = null;
                break;
            }
        }
    }

    public void searchProduct(int id) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != null && arr[i][0].equals(id)) {
                System.out.println("Product found: ");
                System.out.println("ID: " + arr[i][0]);
                System.out.println("Name: " + arr[i][1]);
                System.out.println("Price: " + arr[i][2]);
                System.out.println("Quantity: " + arr[i][3]);
                return;
            } else {
                System.out.println("Product not found.");
            }
        }
    }
    
} 
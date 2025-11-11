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
                return; // stop after adding one product
            }
        }
        System.out.println("Inventory full! Cannot add more products.");
    }

    public void view() {
        System.out.println("ID\tName\tPrice\tQuantity");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != null) {
                System.out.println(arr[i][0] + "\t" + arr[i][1] + "\t" + arr[i][2] + "\t" + arr[i][3]);
            }
        }
    }

    public void update(int id, String newName, double newPrice, int newQuantity) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != null && arr[i][0].equals(id)) {
        arr[i][1] = newName;
        arr[i][2] = newPrice;
        arr[i][3] = newQuantity;    
            }}
    }

    public void delete(int id) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != null && arr[i][0].equals(id)) {
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
            }
        }
    }
    
    public void main(String[] args) {
        // Adding & Viewing
        addProduct(2020,"ps5",2000.0,5);
        addProduct(2025,"PC",4500.0,10);
        addProduct(2018,"Nintendo Switch",1500.0,25);
        view();
        System.out.println(" ");


        // Deleting & Viewing
        delete(2020);
        view();
        System.out.println(" ");

        // Updating & Viewing
        update(2025, "Monitor", 599.99, 16);
        view();
        System.out.println(" ");

        searchProduct(2025);
    }

} 

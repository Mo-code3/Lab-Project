package LabProject.UserInterface;

import java.util.Scanner;
import LabProject.Main.InventoryManager;
import LabProject.Main.Product;
import LabProject.Main.PerishableProduct;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        while (true) {
            System.out.println("\n====== Inventory Management System ======");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Exit");
            System.out.print("Enter choice (1-6): ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    System.out.print("Enter A (Product) OR B (Perishable Product): ");
                    char option = sc.next().charAt(0);

                    if (option == 'A' || option == 'a') {
                        System.out.print("Enter product ID: ");
                        int productId = sc.nextInt();

                        System.out.print("Enter product name: ");
                        String productName = sc.next();

                        System.out.print("Enter product price: ");
                        double productPrice = sc.nextDouble();

                        System.out.print("Enter product quantity: ");
                        int productQuantity = sc.nextInt();

                        // adding as normal Product
                        Product p = new Product(productId, productName, productPrice, productQuantity);
                        manager.addProduct(p);
                        break;

                    } else if (option == 'B' || option == 'b') {
                        System.out.print("Enter product ID: ");
                        int productId = sc.nextInt();

                        System.out.print("Enter product name: ");
                        String productName = sc.next();

                        System.out.print("Enter product price: ");
                        double productPrice = sc.nextDouble();

                        System.out.print("Enter product quantity: ");
                        int productQuantity = sc.nextInt();

                        System.out.print("Enter product expiry date (MM/DD/YYYY): ");
                        String productExpiryDate = sc.next();

                        // adding as normal Product
                        PerishableProduct p = new PerishableProduct(productId, productName, productPrice, productQuantity, productExpiryDate);
                        manager.addProduct(p);
                        break;
                    }

                case 2:
                    manager.view();
                    break;

                case 3:
                    System.out.print("Enter product ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter new product name: ");
                    String updateName = sc.next();
                    System.out.print("Enter new price: ");
                    double updatePrice = sc.nextDouble();
                    System.out.print("Enter new quantity: ");
                    int updateQty = sc.nextInt();
                    manager.update(updateId, updateName, updatePrice, updateQty);
                    break;

                case 4:
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = sc.nextInt();
                    manager.delete(deleteId);
                    break;

                case 5:
                    System.out.print("Enter product ID to search: ");
                    int searchId = sc.nextInt();
                    manager.searchProduct(searchId);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

package LabProject.Main;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   INVENTORY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Exit");
            System.out.println("========================================");
            System.out.print("Enter choice (1-6): ");

            int choice = 0;
            try {
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Clear buffer
                } catch (java.util.InputMismatchException e) {
                    throw new InputMismatchException("Invalid input. Please enter a number between 1 and 6.");
                }

                switch (choice) {
                    case 1: // Add Product
                        try {
                            System.out.print("\nEnter A (Product) OR B (Perishable Product): ");
                            String optionStr = sc.nextLine().trim();
                            
                            if (optionStr.isEmpty()) {
                                System.out.println("Invalid option! Please enter A or B.");
                                break;
                            }
                            
                            char option = optionStr.charAt(0);
        
                            if (option == 'A' || option == 'a') {
                                System.out.print("Enter product ID: ");
                                int productId = sc.nextInt();
                                sc.nextLine(); // Clear buffer
        
                                System.out.print("Enter product name: ");
                                String productName = sc.nextLine();
        
                                System.out.print("Enter product price: ");
                                double productPrice = sc.nextDouble();
                                sc.nextLine(); // Clear buffer
        
                                System.out.print("Enter product quantity: ");
                                int productQuantity = sc.nextInt();
                                sc.nextLine(); // Clear buffer
        
                                Product p = new Product(productId, productName, productPrice, productQuantity);
                                manager.addProduct(p);
        
                            } else if (option == 'B' || option == 'b') {
                                System.out.print("Enter product ID: ");
                                int productId = sc.nextInt();
                                sc.nextLine(); // Clear buffer
        
                                System.out.print("Enter product name: ");
                                String productName = sc.nextLine();
        
                                System.out.print("Enter product price: ");
                                double productPrice = sc.nextDouble();
                                sc.nextLine(); // Clear buffer
        
                                System.out.print("Enter product quantity: ");
                                int productQuantity = sc.nextInt();
                                sc.nextLine(); // Clear buffer
        
                                System.out.print("Enter product expiry date (MM/DD/YYYY): ");
                                String productExpiryDate = sc.nextLine();
        
                                PerishableProduct p = new PerishableProduct(productId, productName, productPrice, productQuantity, productExpiryDate);
                                manager.addProduct(p);
        
                            } else {
                                System.out.println("Invalid option! Please enter A or B.");
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter correct data type.");
                            sc.nextLine(); // Clear buffer
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (InventoryFull e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (DuplicateProductException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2: // View All Products
                        manager.view();
                        break;

                    case 3: // Update Product
                        try {
                            System.out.print("\nEnter product ID to update: ");
                            int updateId = sc.nextInt();
                            sc.nextLine(); // Clear buffer
                            
                            System.out.print("Enter new product name: ");
                            String updateName = sc.nextLine();
                            
                            System.out.print("Enter new price: ");
                            double updatePrice = sc.nextDouble();
                            sc.nextLine(); // Clear buffer
                            
                            System.out.print("Enter new quantity: ");
                            int updateQty = sc.nextInt();
                            sc.nextLine(); // Clear buffer
                            
                            manager.update(updateId, updateName, updatePrice, updateQty);
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter correct data type.");
                            sc.nextLine(); // Clear buffer
                        } catch (ProductNotFound e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4: // Delete Product
                        try {
                            System.out.print("\nEnter product ID to delete: ");
                            int deleteId = sc.nextInt();
                            sc.nextLine(); // Clear buffer
                            
                            System.out.print("Are you sure you want to delete this product? (Y/N): ");
                            String confirm = sc.nextLine().trim();
                            
                            if (confirm.equalsIgnoreCase("Y")) {
                                manager.delete(deleteId);
                            } else {
                                System.out.println("Delete operation cancelled.");
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter correct data type.");
                            sc.nextLine(); // Clear buffer
                        } catch (ProductNotFound e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5: // Search Product
                        try {
                            System.out.print("\nEnter product ID to search: ");
                            int searchId = sc.nextInt();
                            sc.nextLine(); // Clear buffer
                            manager.searchProduct(searchId);
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter correct data type.");
                            sc.nextLine(); // Clear buffer
                        } catch (ProductNotFound e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6: // Exit
                        System.out.println("\n========================================");
                        System.out.println("Thank you for using the system!");
                        System.out.println("Exiting program...");
                        System.out.println("========================================\n");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please enter a number between 1-6.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Clear buffer
                continue;
            }
        }
    }
}
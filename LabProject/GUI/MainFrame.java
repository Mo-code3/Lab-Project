package LabProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class MainFrame extends JFrame {
    
    private InventoryManager manager;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Panels for different screens
    private MenuPanel menuPanel;
    private AddProductPanel addProductPanel;
    private ViewProductsPanel viewProductsPanel;
    private UpdateProductPanel updateProductPanel;
    private DeleteProductPanel deleteProductPanel;
    private SearchProductPanel searchProductPanel;
    
    public MainFrame() {
        manager = new InventoryManager();
        
        // Set up the frame
        setTitle("Inventory Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create card layout for switching panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Initialize all panels
        menuPanel = new MenuPanel(this);
        addProductPanel = new AddProductPanel(this);
        viewProductsPanel = new ViewProductsPanel(this);
        updateProductPanel = new UpdateProductPanel(this);
        deleteProductPanel = new DeleteProductPanel(this);
        searchProductPanel = new SearchProductPanel(this);
        
        // Add panels to card layout
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(addProductPanel, "ADD");
        mainPanel.add(viewProductsPanel, "VIEW");
        mainPanel.add(updateProductPanel, "UPDATE");
        mainPanel.add(deleteProductPanel, "DELETE");
        mainPanel.add(searchProductPanel, "SEARCH");
        
        add(mainPanel);
        
        // Show menu first
        showMenu();
    }
    
    public InventoryManager getManager() {
        return manager;
    }
    
    public void showMenu() {
        cardLayout.show(mainPanel, "MENU");
    }
    
    public void showAddProduct() {
        addProductPanel.clearFields();
        cardLayout.show(mainPanel, "ADD");
    }
    
    public void showViewProducts() {
        viewProductsPanel.refreshProducts();
        cardLayout.show(mainPanel, "VIEW");
    }
    
    public void showUpdateProduct() {
        updateProductPanel.clearFields();
        cardLayout.show(mainPanel, "UPDATE");
    }
    
    public void showDeleteProduct() {
        deleteProductPanel.clearFields();
        cardLayout.show(mainPanel, "DELETE");
    }
    
    public void showSearchProduct() {
        searchProductPanel.clearFields();
        cardLayout.show(mainPanel, "SEARCH");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
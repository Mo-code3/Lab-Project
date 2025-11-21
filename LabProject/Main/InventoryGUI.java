package LabProject.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class InventoryGUI extends JFrame {
    
    private InventoryManager manager;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Field references for different panels
    private JTextField addIdField, addNameField, addPriceField, addQuantityField, addExpiryDateField;
    private JRadioButton regularProductBtn, perishableProductBtn;
    private JLabel expiryDateLabel;
    private ButtonGroup productTypeGroup;
    
    private JTable productTable;
    private DefaultTableModel tableModel;
    
    private JTextField updateIdField, updateNameField, updatePriceField, updateQuantityField;
    private JTextField deleteIdField;
    private JTextField searchIdField;
    private JTextArea searchResultArea;
    
    public InventoryGUI() {
        manager = new InventoryManager();
        
        setTitle("Inventory Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create all panels
        mainPanel.add(createMenuPanel(), "MENU");
        mainPanel.add(createAddProductPanel(), "ADD");
        mainPanel.add(createViewProductsPanel(), "VIEW");
        mainPanel.add(createUpdateProductPanel(), "UPDATE");
        mainPanel.add(createDeleteProductPanel(), "DELETE");
        mainPanel.add(createSearchProductPanel(), "SEARCH");
        
        add(mainPanel);
        showMenu();
    }
    
    // ==================== MENU PANEL ====================
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        titlePanel.setPreferredSize(new Dimension(0, 100));
        
        JLabel titleLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        JButton addBtn = createMenuButton("Add Product", new Color(46, 204, 113));
        JButton viewBtn = createMenuButton("View All Products", new Color(52, 152, 219));
        JButton updateBtn = createMenuButton("Update Product", new Color(241, 196, 15));
        JButton deleteBtn = createMenuButton("Delete Product", new Color(231, 76, 60));
        JButton searchBtn = createMenuButton("Search Product", new Color(155, 89, 182));
        JButton exitBtn = createMenuButton("Exit", new Color(149, 165, 166));
        
        addBtn.addActionListener(e -> showAddProduct());
        viewBtn.addActionListener(e -> showViewProducts());
        updateBtn.addActionListener(e -> showUpdateProduct());
        deleteBtn.addActionListener(e -> showDeleteProduct());
        searchBtn.addActionListener(e -> showSearchProduct());
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", 
                "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
        
        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(exitBtn);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton createMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    // ==================== ADD PRODUCT PANEL ====================
    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 204, 113));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        JLabel titleLabel = new JLabel("ADD PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Product Type
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(new Color(240, 240, 245));
        JLabel typeLabel = new JLabel("Product Type:");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        regularProductBtn = new JRadioButton("Regular Product", true);
        perishableProductBtn = new JRadioButton("Perishable Product");
        productTypeGroup = new ButtonGroup();
        productTypeGroup.add(regularProductBtn);
        productTypeGroup.add(perishableProductBtn);
        typePanel.add(typeLabel);
        typePanel.add(regularProductBtn);
        typePanel.add(perishableProductBtn);
        formPanel.add(typePanel, gbc);
        
        // Fields
        gbc.gridwidth = 1;
        addIdField = addFormField(formPanel, "Product ID:", 1, gbc);
        addNameField = addFormField(formPanel, "Product Name:", 2, gbc);
        addPriceField = addFormField(formPanel, "Price:", 3, gbc);
        addQuantityField = addFormField(formPanel, "Quantity:", 4, gbc);
        
        gbc.gridy = 5; gbc.gridx = 0;
        expiryDateLabel = new JLabel("Expiry Date (MM/DD/YYYY):");
        expiryDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        expiryDateLabel.setVisible(false);
        formPanel.add(expiryDateLabel, gbc);
        
        gbc.gridx = 1;
        addExpiryDateField = new JTextField(20);
        addExpiryDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        addExpiryDateField.setVisible(false);
        formPanel.add(addExpiryDateField, gbc);
        
        regularProductBtn.addActionListener(e -> {
            expiryDateLabel.setVisible(false);
            addExpiryDateField.setVisible(false);
            panel.revalidate();
            panel.repaint();
        });
        
        perishableProductBtn.addActionListener(e -> {
            expiryDateLabel.setVisible(true);
            addExpiryDateField.setVisible(true);
            panel.revalidate();
            panel.repaint();
        });
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.addActionListener(e -> addProduct());
        
        JButton backButton = createBackButton();
        
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addProduct() {
        try {
            int id = Integer.parseInt(addIdField.getText().trim());
            String name = addNameField.getText().trim();
            double price = Double.parseDouble(addPriceField.getText().trim());
            int quantity = Integer.parseInt(addQuantityField.getText().trim());
            
            if (regularProductBtn.isSelected()) {
                Product product = new Product(id, name, price, quantity);
                manager.addProduct(product);
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAddFields();
            } else {
                String expiryDate = addExpiryDateField.getText().trim();
                PerishableProduct product = new PerishableProduct(id, name, price, quantity, expiryDate);
                manager.addProduct(product);
                JOptionPane.showMessageDialog(this, "Perishable product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAddFields();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | InventoryFull | DuplicateProductException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearAddFields() {
        addIdField.setText("");
        addNameField.setText("");
        addPriceField.setText("");
        addQuantityField.setText("");
        addExpiryDateField.setText("");
        regularProductBtn.setSelected(true);
        expiryDateLabel.setVisible(false);
        addExpiryDateField.setVisible(false);
    }
    
    // ==================== VIEW PRODUCTS PANEL ====================
    private JPanel createViewProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        JLabel titleLabel = new JLabel("ALL PRODUCTS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Table
        String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity", "Type", "Expiry Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        productTable = new JTable(tableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 13));
        productTable.setRowHeight(25);
        productTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        productTable.getTableHeader().setBackground(new Color(52, 152, 219));
        productTable.getTableHeader().setForeground(Color.WHITE);
        productTable.setSelectionBackground(new Color(155, 89, 182));
        productTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(new Color(52, 152, 219));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.setPreferredSize(new Dimension(150, 40));
        refreshButton.addActionListener(e -> refreshProducts());
        
        JButton backButton = createBackButton();
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void refreshProducts() {
        tableModel.setRowCount(0);
        Product[] products = manager.getAllProducts();
        
        for (Product p : products) {
            if (p != null) {
                if (p instanceof PerishableProduct) {
                    PerishableProduct pp = (PerishableProduct) p;
                    tableModel.addRow(new Object[]{
                        pp.getProductID(), pp.getProductName(), 
                        String.format("$%.2f", pp.getPrice()), pp.getQuantity(),
                        "Perishable", pp.getExpiryDate()
                    });
                } else {
                    tableModel.addRow(new Object[]{
                        p.getProductID(), p.getProductName(),
                        String.format("$%.2f", p.getPrice()), p.getQuantity(),
                        "Regular", "N/A"
                    });
                }
            }
        }
    }
    
    // ==================== UPDATE PRODUCT PANEL ====================
    private JPanel createUpdateProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(241, 196, 15));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        JLabel titleLabel = new JLabel("UPDATE PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        updateIdField = addFormField(formPanel, "Product ID:", 0, gbc);
        updateNameField = addFormField(formPanel, "New Product Name:", 1, gbc);
        updatePriceField = addFormField(formPanel, "New Price:", 2, gbc);
        updateQuantityField = addFormField(formPanel, "New Quantity:", 3, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton updateButton = new JButton("Update Product");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(241, 196, 15));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setPreferredSize(new Dimension(150, 40));
        updateButton.addActionListener(e -> updateProduct());
        
        JButton backButton = createBackButton();
        
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void updateProduct() {
        try {
            int id = Integer.parseInt(updateIdField.getText().trim());
            String name = updateNameField.getText().trim();
            double price = Double.parseDouble(updatePriceField.getText().trim());
            int quantity = Integer.parseInt(updateQuantityField.getText().trim());
            
            manager.update(id, name, price, quantity);
            JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearUpdateFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ProductNotFound | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearUpdateFields() {
        updateIdField.setText("");
        updateNameField.setText("");
        updatePriceField.setText("");
        updateQuantityField.setText("");
    }
    
    // ==================== DELETE PRODUCT PANEL ====================
    private JPanel createDeleteProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(231, 76, 60));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        JLabel titleLabel = new JLabel("DELETE PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        deleteIdField = addFormField(formPanel, "Product ID to Delete:", 0, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.addActionListener(e -> deleteProduct());
        
        JButton backButton = createBackButton();
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void deleteProduct() {
        try {
            int id = Integer.parseInt(deleteIdField.getText().trim());
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete product with ID " + id + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                manager.delete(id);
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                deleteIdField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ProductNotFound e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Product Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ==================== SEARCH PRODUCT PANEL ====================
    private JPanel createSearchProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 245));
        
        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(155, 89, 182));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        JLabel titleLabel = new JLabel("SEARCH PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // Search Form
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setMaximumSize(new Dimension(900, 60));
        
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel);
        
        searchIdField = new JTextField(20);
        searchIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(searchIdField);
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(new Color(155, 89, 182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setPreferredSize(new Dimension(120, 35));
        searchButton.addActionListener(e -> searchProduct());
        formPanel.add(searchButton);
        
        // Result Panel
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(new Color(240, 240, 245));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel resultLabel = new JLabel("Search Results:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        searchResultArea = new JTextArea(10, 50);
        searchResultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        searchResultArea.setEditable(false);
        searchResultArea.setBackground(Color.WHITE);
        searchResultArea.setBorder(BorderFactory.createLineBorder(new Color(155, 89, 182), 2));
        
        JScrollPane scrollPane = new JScrollPane(searchResultArea);
        
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        
        centerPanel.add(formPanel);
        centerPanel.add(resultPanel);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        JButton backButton = createBackButton();
        buttonPanel.add(backButton);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void searchProduct() {
        try {
            int id = Integer.parseInt(searchIdField.getText().trim());
            Product[] products = manager.getAllProducts();
            Product foundProduct = null;
            
            for (Product p : products) {
                if (p != null && p.getProductID() == id) {
                    foundProduct = p;
                    break;
                }
            }
            
            if (foundProduct != null) {
                StringBuilder result = new StringBuilder();
                result.append("========== Product Found ==========\n\n");
                result.append("Product ID: ").append(foundProduct.getProductID()).append("\n");
                result.append("Product Name: ").append(foundProduct.getProductName()).append("\n");
                result.append("Price: $").append(String.format("%.2f", foundProduct.getPrice())).append("\n");
                result.append("Quantity: ").append(foundProduct.getQuantity()).append("\n");
                
                if (foundProduct instanceof PerishableProduct) {
                    PerishableProduct pp = (PerishableProduct) foundProduct;
                    result.append("Type: Perishable Product\n");
                    result.append("Expiry Date: ").append(pp.getExpiryDate()).append("\n");
                } else {
                    result.append("Type: Regular Product\n");
                }
                
                result.append("\n===================================");
                searchResultArea.setText(result.toString());
            } else {
                searchResultArea.setText("Product with ID " + id + " not found!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
            searchResultArea.setText("");
        }
    }
    
    // ==================== HELPER METHODS ====================
    private JTextField addFormField(JPanel panel, String labelText, int row, GridBagConstraints gbc) {
        gbc.gridy = row;
        gbc.gridx = 0;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(field, gbc);
        
        return field;
    }
    
    private JButton createBackButton() {
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> showMenu());
        return backButton;
    }
    
    // Navigation methods
    public void showMenu() {
        cardLayout.show(mainPanel, "MENU");
    }
    
    public void showAddProduct() {
        clearAddFields();
        cardLayout.show(mainPanel, "ADD");
    }
    
    public void showViewProducts() {
        refreshProducts();
        cardLayout.show(mainPanel, "VIEW");
    }
    
    public void showUpdateProduct() {
        clearUpdateFields();
        cardLayout.show(mainPanel, "UPDATE");
    }
    
    public void showDeleteProduct() {
        deleteIdField.setText("");
        cardLayout.show(mainPanel, "DELETE");
    }
    
    public void showSearchProduct() {
        searchIdField.setText("");
        searchResultArea.setText("");
        cardLayout.show(mainPanel, "SEARCH");
    }
    
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryGUI().setVisible(true));
    }
}

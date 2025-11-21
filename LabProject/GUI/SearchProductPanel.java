package LabProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class SearchProductPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField idField;
    private JTextArea resultArea;
    
    public SearchProductPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(155, 89, 182));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("SEARCH PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Center Panel (contains search form and results)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // Search Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setMaximumSize(new Dimension(900, 60));
        
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel);
        
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(idField);
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(new Color(155, 89, 182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setPreferredSize(new Dimension(120, 35));
        formPanel.add(searchButton);
        
        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(new Color(240, 240, 245));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel resultLabel = new JLabel("Search Results:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        resultArea = new JTextArea(10, 50);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setEditable(false);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(155, 89, 182), 2));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);
        
        centerPanel.add(formPanel);
        centerPanel.add(resultPanel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        
        buttonPanel.add(backButton);
        
        // Add action listeners
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMenu();
            }
        });
        
        // Add components to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void searchProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            
            Product[] products = mainFrame.getManager().getAllProducts();
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
                resultArea.setText(result.toString());
            } else {
                resultArea.setText("Product with ID " + id + " not found!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
            resultArea.setText("");
        }
    }
    
    public void clearFields() {
        idField.setText("");
        resultArea.setText("");
    }
}

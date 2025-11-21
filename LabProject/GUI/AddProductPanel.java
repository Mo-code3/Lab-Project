package LabProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class AddProductPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField idField, nameField, priceField, quantityField, expiryDateField;
    private JRadioButton regularProductBtn, perishableProductBtn;
    private JLabel expiryDateLabel;
    private ButtonGroup productTypeGroup;
    
    public AddProductPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 204, 113));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("ADD PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Product Type Selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
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
        
        // Product ID
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel, gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(idField, gbc);
        
        // Product Name
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);
        
        // Product Price
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(priceLabel, gbc);
        
        gbc.gridx = 1;
        priceField = new JTextField(20);
        priceField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(priceField, gbc);
        
        // Product Quantity
        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(quantityLabel, gbc);
        
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(quantityField, gbc);
        
        // Expiry Date (for perishable products)
        gbc.gridy = 5;
        gbc.gridx = 0;
        expiryDateLabel = new JLabel("Expiry Date (MM/DD/YYYY):");
        expiryDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        expiryDateLabel.setVisible(false);
        formPanel.add(expiryDateLabel, gbc);
        
        gbc.gridx = 1;
        expiryDateField = new JTextField(20);
        expiryDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        expiryDateField.setVisible(false);
        formPanel.add(expiryDateField, gbc);
        
        // Add listeners to radio buttons
        regularProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expiryDateLabel.setVisible(false);
                expiryDateField.setVisible(false);
                revalidate();
                repaint();
            }
        });
        
        perishableProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expiryDateLabel.setVisible(true);
                expiryDateField.setVisible(true);
                revalidate();
                repaint();
            }
        });
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setPreferredSize(new Dimension(150, 40));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        
        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMenu();
            }
        });
        
        // Add components to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            
            if (regularProductBtn.isSelected()) {
                Product product = new Product(id, name, price, quantity);
                mainFrame.getManager().addProduct(product);
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                String expiryDate = expiryDateField.getText().trim();
                PerishableProduct product = new PerishableProduct(id, name, price, quantity, expiryDate);
                mainFrame.getManager().addProduct(product);
                JOptionPane.showMessageDialog(this, "Perishable product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID, Price, and Quantity!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (InventoryFull e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Inventory Full", JOptionPane.ERROR_MESSAGE);
        } catch (DuplicateProductException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Duplicate Product", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        expiryDateField.setText("");
        regularProductBtn.setSelected(true);
        expiryDateLabel.setVisible(false);
        expiryDateField.setVisible(false);
    }
}

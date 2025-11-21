package LabProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class UpdateProductPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField idField, nameField, priceField, quantityField;
    
    public UpdateProductPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(241, 196, 15));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("UPDATE PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Product ID
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel, gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(idField, gbc);
        
        // Product Name
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel("New Product Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);
        
        // Product Price
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel priceLabel = new JLabel("New Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(priceLabel, gbc);
        
        gbc.gridx = 1;
        priceField = new JTextField(20);
        priceField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(priceField, gbc);
        
        // Product Quantity
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel quantityLabel = new JLabel("New Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(quantityLabel, gbc);
        
        gbc.gridx = 1;
        quantityField = new JTextField(20);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(quantityField, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton updateButton = new JButton("Update Product");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(241, 196, 15));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setPreferredSize(new Dimension(150, 40));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        
        // Add action listeners
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
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
    
    private void updateProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            
            mainFrame.getManager().update(id, name, price, quantity);
            JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ProductNotFound e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Product Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}

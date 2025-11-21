package LabProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class DeleteProductPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField idField;
    
    public DeleteProductPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(231, 76, 60));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("DELETE PRODUCT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Product ID
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel idLabel = new JLabel("Product ID to Delete:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel, gbc);
        
        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(idField, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setPreferredSize(new Dimension(150, 40));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        // Add action listeners
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
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
    
    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete product with ID " + id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                mainFrame.getManager().delete(id);
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ProductNotFound e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Product Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void clearFields() {
        idField.setText("");
    }
}

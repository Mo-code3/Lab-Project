package LabProject.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LabProject.Main.*;

public class ViewProductsPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTable productTable;
    private DefaultTableModel tableModel;
    
    public ViewProductsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("ALL PRODUCTS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Table setup
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
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(new Color(52, 152, 219));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.setPreferredSize(new Dimension(150, 40));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(150, 40));
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        
        // Add action listeners
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshProducts();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMenu();
            }
        });
        
        // Add components to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void refreshProducts() {
        tableModel.setRowCount(0);
        Product[] products = mainFrame.getManager().getAllProducts();
        
        for (Product p : products) {
            if (p != null) {
                if (p instanceof PerishableProduct) {
                    PerishableProduct pp = (PerishableProduct) p;
                    tableModel.addRow(new Object[]{
                        pp.getProductID(),
                        pp.getProductName(),
                        String.format("$%.2f", pp.getPrice()),
                        pp.getQuantity(),
                        "Perishable",
                        pp.getExpiryDate()
                    });
                } else {
                    tableModel.addRow(new Object[]{
                        p.getProductID(),
                        p.getProductName(),
                        String.format("$%.2f", p.getPrice()),
                        p.getQuantity(),
                        "Regular",
                        "N/A"
                    });
                }
            }
        }
    }
}

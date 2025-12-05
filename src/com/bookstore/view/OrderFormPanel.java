package com.bookstore.view;

import com.bookstore.utils.InputValidator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OrderFormPanel extends JPanel {
    private JTextField firstNameField, lastNameField, phoneField, addressField;
    private MainFrame frame;
    private JLabel errorLabel;

    public OrderFormPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
        RoundedPanel header = createHeader();
        add(header, BorderLayout.NORTH);

        // Title
        JLabel title = new JLabel("YOUR ORDER");
        title.setFont(new Font("SansSerif", Font.BOLD, 28)); // Larger, modern font
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(20, 0, 30, 0)); // More space
        add(title, BorderLayout.NORTH);

        // Form container (fills center)
        RoundedPanel formContainer = new RoundedPanel(16); // Bigger rounding
        formContainer.setLayout(new BorderLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        RoundedPanel formPanel = createFormPanel();
        formContainer.add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        RoundedPanel buttonPanel = createButtonPanel();
        formContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(formContainer, BorderLayout.CENTER);
    }

    private RoundedPanel createHeader() {
        RoundedPanel header = new RoundedPanel(12); // Bigger rounding
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel logo = new JLabel("📖 Story time ★"); // Added space
        logo.setFont(new Font("SansSerif", Font.ITALIC, 24)); // Larger, modern
        logo.setForeground(Color.BLACK);
        header.add(logo, BorderLayout.WEST);

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0)); // More space
        rightIcons.setBackground(Color.WHITE);

        JButton searchBtn = Theme.createIconButton("🔍", e -> frame.navigateTo("SEARCH"));
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 18)); // Larger
        JButton favBtn = Theme.createIconButton("♡", e -> frame.navigateTo("WISHLIST"));
        favBtn.setFont(new Font("SansSerif", Font.BOLD, 18)); // Larger
        JButton cartBtn = Theme.createIconButton("🛒", e -> frame.navigateTo("CART"));
        cartBtn.setFont(new Font("SansSerif", Font.BOLD, 18)); // Larger
        rightIcons.add(searchBtn);
        rightIcons.add(favBtn);
        rightIcons.add(cartBtn);
        header.add(rightIcons, BorderLayout.EAST);

        // Back button below logo
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Color.WHITE);
        JButton backBtn = Theme.createIconButton("← Back", e -> frame.navigateTo("CART"));
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16)); // Larger
        backPanel.add(backBtn);
        header.add(backPanel, BorderLayout.SOUTH);

        return header;
    }

    private RoundedPanel createFormPanel() {
        RoundedPanel formPanel = new RoundedPanel(12); // Bigger rounding
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Consistent border
        formPanel.setPreferredSize(new Dimension(450, 400)); // Taller for stacked fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20); // More space
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;

        // First name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel firstNameLabel = new JLabel("FIRST NAME");
        firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); // Larger
        firstNameLabel.setForeground(Color.BLACK);
        formPanel.add(firstNameLabel, gbc);
        gbc.gridy = 1;
        firstNameField = new JTextField(30); // Full width
        firstNameField.setFont(new Font("SansSerif", Font.BOLD, 14)); // Bold input
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        formPanel.add(firstNameField, gbc);

        // Last name
        gbc.gridy = 2;
        JLabel lastNameLabel = new JLabel("LAST NAME");
        lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        lastNameLabel.setForeground(Color.BLACK);
        formPanel.add(lastNameLabel, gbc);
        gbc.gridy = 3;
        lastNameField = new JTextField(30); // Full width
        lastNameField.setFont(new Font("SansSerif", Font.BOLD, 14)); // Bold input
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        formPanel.add(lastNameField, gbc);

        // Phone
        gbc.gridy = 4;
        JLabel phoneLabel = new JLabel("PHONE NUMBER");
        phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        phoneLabel.setForeground(Color.BLACK);
        formPanel.add(phoneLabel, gbc);
        gbc.gridy = 5;
        phoneField = new JTextField("+213", 30); // Full width
        phoneField.setFont(new Font("SansSerif", Font.BOLD, 14)); // Bold input
        phoneField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        formPanel.add(phoneField, gbc);

        // Address
        gbc.gridy = 6;
        JLabel addressLabel = new JLabel("DELIVERY ADDRESS");
        addressLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        addressLabel.setForeground(Color.BLACK);
        formPanel.add(addressLabel, gbc);
        gbc.gridy = 7;
        addressField = new JTextField(30); // Full width
        addressField.setFont(new Font("SansSerif", Font.BOLD, 14)); // Bold input
        addressField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        formPanel.add(addressField, gbc);

        // Error label
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Larger
        formPanel.add(errorLabel, gbc);

        return formPanel;
    }

    private RoundedPanel createButtonPanel() {
        RoundedPanel buttonPanel = new RoundedPanel(12); // Bigger rounding
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15)); // More space
        buttonPanel.setBackground(Color.WHITE);
        JButton cancelBtn = Theme.createThemedButton("CANCEL", 16, e -> frame.navigateTo("CART")); // Larger font
        cancelBtn.setBackground(Color.LIGHT_GRAY); // Gray background
        cancelBtn.setForeground(Color.BLACK); // White text
        JButton checkoutBtn = Theme.createThemedButton("CHECKOUT", 16, e -> handleCheckout()); // Larger font
        checkoutBtn.setBackground(new Color(0, 128, 0));
        //checkoutBtn.setBackground(Color.BLACK); // Black background
        checkoutBtn.setForeground(Color.WHITE); // White text
        buttonPanel.add(cancelBtn);
        buttonPanel.add(checkoutBtn);
        return buttonPanel;
    }

    private void handleCheckout() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String fullName = firstName + " " + lastName;
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields (name, phone, address).", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        errorLabel.setText("");
        if (!InputValidator.validateName(fullName)) {
            firstNameField.requestFocus();
            firstNameField.selectAll();
            return;
        }
        if (!InputValidator.validatePhone(phone)) {
            phoneField.requestFocus();
            phoneField.selectAll();
            return;
        }
        if (!InputValidator.validateAddress(address)) {
            addressField.requestFocus();
            addressField.selectAll();
            return;
        }
        String successMsg = String.format("Order confirmed :) for %s!\nPhone: %s\nAddress: %s\nRedirecting to home...", fullName, phone, address);
        JOptionPane.showMessageDialog(this, successMsg, "Order Placed Successfully ! ", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
        frame.navigateTo("HOME");
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("+213");
        addressField.setText("");
    }
}
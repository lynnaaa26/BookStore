package com.bookstore.view;

import com.bookstore.utils.InputValidator;
import javax.swing.*;
import java.awt.*;

public class OrderFormPanel extends JPanel {
    private JTextField firstNameField, lastNameField, phoneField, addressField;
    private MainFrame frame;
    private JLabel errorLabel;

    public OrderFormPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout(20, 20));
        setBackground(Theme.BEIGE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        RoundedPanel header = createHeader();
        add(header, BorderLayout.NORTH);

        // Title
        JLabel title = new JLabel("YOUR ORDER");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Theme.SADDLE_BROWN);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH); // Now above form

        // Form container
        RoundedPanel formContainer = new RoundedPanel(12);
        formContainer.setLayout(new BorderLayout());
        formContainer.setBackground(Theme.BEIGE);

        RoundedPanel formPanel = createFormPanel();
        formContainer.add(formPanel, BorderLayout.NORTH);

        // Buttons panel
        RoundedPanel buttonPanel = createButtonPanel();
        formContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(formContainer, BorderLayout.CENTER);
    }

    private RoundedPanel createHeader() {
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Theme.BEIGE);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel logo = new JLabel("📖 Story time★");
        logo.setFont(new Font("Serif", Font.ITALIC, 20));
        logo.setForeground(Theme.SADDLE_BROWN);
        header.add(logo, BorderLayout.WEST);

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightIcons.setBackground(Theme.BEIGE);
        JButton searchBtn = Theme.createIconButton("🔍", e -> frame.navigateTo("SEARCH"));
        JButton favBtn = Theme.createIconButton("♡", e -> frame.navigateTo("WISHLIST"));
        JButton cartBtn = Theme.createIconButton("🛒", e -> frame.navigateTo("CART"));
        rightIcons.add(searchBtn);
        rightIcons.add(favBtn);
        rightIcons.add(cartBtn);
        header.add(rightIcons, BorderLayout.EAST);

        // Back button below logo
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Theme.BEIGE);
        JButton backBtn = Theme.createIconButton("← Back", e -> frame.navigateTo("CART"));
        backPanel.add(backBtn);
        header.add(backPanel, BorderLayout.SOUTH);

        return header;
    }

    private RoundedPanel createFormPanel() {
        RoundedPanel formPanel = new RoundedPanel(8);
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        formPanel.setPreferredSize(new Dimension(350, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // First name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel firstNameLabel = new JLabel("FIRST NAME");
        firstNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        firstNameLabel.setForeground(Theme.SADDLE_BROWN);
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        firstNameField.setFont(new Font("Serif", Font.PLAIN, 12));
        firstNameField.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        formPanel.add(firstNameField, gbc);

        // Last name
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lastNameLabel = new JLabel("LAST NAME");
        lastNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        lastNameLabel.setForeground(Theme.SADDLE_BROWN);
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        lastNameField.setFont(new Font("Serif", Font.PLAIN, 12));
        lastNameField.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        formPanel.add(lastNameField, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel phoneLabel = new JLabel("PHONE NUMBER");
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 12));
        phoneLabel.setForeground(Theme.SADDLE_BROWN);
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        phoneField = new JTextField("+213", 15);
        phoneField.setFont(new Font("Serif", Font.PLAIN, 12));
        phoneField.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        formPanel.add(phoneField, gbc);

        // Address
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel addressLabel = new JLabel("DELIVERY ADDRESS");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 12));
        addressLabel.setForeground(Theme.SADDLE_BROWN);
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addressField = new JTextField(15);
        addressField.setFont(new Font("Serif", Font.PLAIN, 12));
        addressField.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        formPanel.add(addressField, gbc);

        // Error label
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        formPanel.add(errorLabel, gbc);

        return formPanel;
    }

    private RoundedPanel createButtonPanel() {
        RoundedPanel buttonPanel = new RoundedPanel(8);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Theme.BEIGE);

        JButton cancelBtn = Theme.createThemedButton("CANCEL", 13, e -> frame.navigateTo("CART"));
        JButton checkoutBtn = Theme.createThemedButton("CHECKOUT", 13, e -> handleCheckout());

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
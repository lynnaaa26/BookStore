package com.bookstore.view;  

import javax.swing.*;  
import java.awt.*;  
import com.bookstore.utils.InputValidator;  
public class OrderFormPanel extends JPanel { 
    
    
    private JTextField firstNameField, lastNameField, phoneField, addressField;  // input data
    private MainFrame frame;  //  parent frame
    private JLabel errorLabel;  //  error display 


    
        OrderFormPanel(MainFrame frame) { 
        this.frame = frame;  // store the frame reference enables panel to talk back to MainFrame.
        setLayout(null);  
        setBackground(new Color(245, 245, 220));  //  beige background
        
        // ---------- Logo and navigation buttons ----------
        
        JLabel logo = new JLabel("📖 Story time★");  
        logo.setFont(new Font("Serif", Font.ITALIC, 20));  
        logo.setForeground(new Color(101, 67, 33));  // brown color 
        logo.setBounds(40, 20, 200, 30);  // positions  for header.
        add(logo);  // adding to  jpanel.

        // back button arrow for returning to cart
        JButton backBtn = new JButton("← Back");  // jbutton for clickable action
        backBtn.setFocusPainted(false);  // hides focus rectangle cleaner look 
        backBtn.setContentAreaFilled(false);  // transparent background 
        backBtn.setBorderPainted(false);  // No border
        backBtn.setFont(new Font("Serif", Font.PLAIN, 14)); 
        backBtn.setForeground(new Color(101, 67, 33));  // brown text.
        backBtn.setBounds(40, 60, 100, 25);  // position below logo.
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        backBtn.addActionListener(e -> frame.navigateTo("CART"));  // Lambda listener: On click, navigate to CART panel via frame method.
        add(backBtn);  // add to panel.

        
        JButton searchBtn = new JButton("🔍"); 
        searchBtn.setFocusPainted(false);  
        searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false);
        searchBtn.setFont(new Font("Serif", Font.PLAIN, 20));  
        searchBtn.setForeground(new Color(101, 67, 33));
        searchBtn.setBounds(800, 25, 50, 30);  
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBtn.addActionListener(e -> frame.navigateTo("SEARCH"));  // navigates to search panel.
        add(searchBtn);

        // Wishlist button
        JButton favBtn = new JButton("♡");  
        favBtn.setFocusPainted(false);
        favBtn.setContentAreaFilled(false);
        favBtn.setBorderPainted(false);
        favBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        favBtn.setForeground(new Color(101, 67, 33));
        favBtn.setBounds(845, 25, 50, 30);  // next to search
        favBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        favBtn.addActionListener(e -> frame.navigateTo("WISHLIST"));  
        add(favBtn);

        // cart button
        JButton cartBtn = new JButton("🛒");  
        cartBtn.setFocusPainted(false);
        cartBtn.setContentAreaFilled(false);
        cartBtn.setBorderPainted(false);
        cartBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        cartBtn.setForeground(new Color(101, 67, 33));
        cartBtn.setBounds(890, 25, 50, 30);  // far right
        cartBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartBtn.addActionListener(e -> frame.navigateTo("CART"));  // back to CART.
        add(cartBtn);

        // ---------- title  ----------
        JLabel title = new JLabel("YOUR ORDER");   
        title.setFont(new Font("Serif", Font.BOLD, 20));  
        title.setForeground(new Color(101, 67, 33));
        title.setBounds(380, 130, 200, 30);  // centered horizontally
        add(title);

        // ----------  user details ----------
        
        JPanel formPanel = new JPanel();  // sub panel for form grouping 
        formPanel.setLayout(new GridLayout(5, 2, 10, 15));  // 5 rows  2 cols 
        formPanel.setBackground(Color.WHITE);  
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1));  //  beige border.
        formPanel.setBounds(300, 180, 350, 220); 
        add(formPanel);  // adds to main panel

        // first name label and field
        JLabel firstNameLabel = new JLabel("FIRST NAME"); 
        firstNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        firstNameLabel.setForeground(new Color(101, 67, 33));
        firstNameField = new JTextField();  
        firstNameField.setFont(new Font("Serif", Font.PLAIN, 12));  
        firstNameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));  
        firstNameField.setToolTipText("Enter first name (letters only, e.g., John)"); 

        // last name
        JLabel lastNameLabel = new JLabel("LAST NAME");
        lastNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        lastNameLabel.setForeground(new Color(101, 67, 33));
        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Serif", Font.PLAIN, 12));
        lastNameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        lastNameField.setToolTipText("Enter last name (letters only, e.g., Doe)");

        // phone
        JLabel phoneLabel = new JLabel("PHONE NUMBER");
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 12));
        phoneLabel.setForeground(new Color(101, 67, 33));
        phoneField = new JTextField("+213");  
        phoneField.setFont(new Font("Serif", Font.PLAIN, 12));
        phoneField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        phoneField.setToolTipText("Enter phone starting with +213 followed by 9 digits (e.g., +213661234567)");

        // address
        JLabel addressLabel = new JLabel("DELIVERY ADDRESS");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 12));
        addressLabel.setForeground(new Color(101, 67, 33));
        addressField = new JTextField();
        addressField.setFont(new Font("Serif", Font.PLAIN, 12));
        addressField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        addressField.setToolTipText("Enter full delivery address (street, city, ZIP; min 10 chars)");

        // adding labels and fields to formPanel
        formPanel.add(firstNameLabel);  
        formPanel.add(firstNameField);  
        formPanel.add(lastNameLabel);   
        formPanel.add(lastNameField);   
        formPanel.add(phoneLabel);      
        formPanel.add(phoneField);      
        formPanel.add(addressLabel);    
        formPanel.add(addressField);    

        // error display 
        formPanel.add(new JLabel(""));  // empty ).
        errorLabel = new JLabel("");  // initializes error label
        errorLabel.setForeground(Color.RED);  
        errorLabel.setFont(new Font("Serif", Font.PLAIN, 10));  
        formPanel.add(errorLabel);  

        // ---------Cancel and Checkout ----------
        //  Returns to cart without changes
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(new Color(220, 220, 200));  
        cancelBtn.setForeground(new Color(101, 67, 33));  
        cancelBtn.setFocusPainted(false);
        cancelBtn.setFont(new Font("Serif", Font.BOLD, 13));
        cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        cancelBtn.setBounds(350, 420, 120, 35);  // position below form
        cancelBtn.addActionListener(e -> frame.navigateTo("CART"));  // navigates back
        add(cancelBtn);

        // checkout  validation and order processing
        JButton checkoutBtn = new JButton("CHECKOUT");
        checkoutBtn.setBackground(new Color(220, 220, 200));  
        checkoutBtn.setForeground(new Color(101, 67, 33));
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setFont(new Font("Serif", Font.BOLD, 13));
        checkoutBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        checkoutBtn.setBounds(500, 420, 120, 35);  
        checkoutBtn.addActionListener(e -> handleCheckout());  
        add(checkoutBtn);
    }  
    //handles checkout  validates inputs step by step shows clear messages processes on success
    private void handleCheckout() { 
        
        String firstName = firstNameField.getText().trim(); 
        String lastName = lastNameField.getText().trim();
        String fullName = firstName + " " + lastName;  // combines for single name 
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();

        // quick check for obvious empties
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()) {  // isEmpty() after trim catches whitespace 
            JOptionPane.showMessageDialog(this,  // 'this' as parent: Modal dialog tied to panel.
                "Please fill all required fields (name, phone, address).", 
                "Missing Input", JOptionPane.WARNING_MESSAGE);  // Warning icon—less alarming than error.
            return;  // Exits method early—no further processing.
        }

        // Clear any prior errors
        if (errorLabel != null) {
            errorLabel.setText("");  // Empties label.
        }

        
        if (!InputValidator.validateName(fullName)) {  // Checks letters/spaces only 
            firstNameField.requestFocus();  // Shifts focus to field
            firstNameField.selectAll();  // Selects text—user can type over instantly
            return;  // Stops here—clarity: One error at a time
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

        // Success: All checks passed—celebrate with formatted message.
        String successMsg = String.format(  // format() for dynamic text (like printf in C).
            "Order confirmed :) for %s!\nPhone: %s\nAddress: %s\nRedirecting to home...", 
            fullName, phone, address  //  placeholders 
        );
        JOptionPane.showMessageDialog(this, successMsg, 
            "Order Placed Successfully ! ", JOptionPane.INFORMATION_MESSAGE); 
        
        clearFields();

        
        frame.navigateTo("HOME");
    }  

    private void clearFields() {  // helper
        firstNameField.setText("");  // empties text
        lastNameField.setText("");
        phoneField.setText("+213");  
        addressField.setText("");
    }  
}  
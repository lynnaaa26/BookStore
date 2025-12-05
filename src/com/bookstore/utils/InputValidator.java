package com.bookstore.utils;

import javax.swing.JOptionPane;

public class InputValidator {  

    public static boolean validatePhone(String phone) {
        if (phone == null || !phone.startsWith("+213") || phone.length() != 13) {
            JOptionPane.showMessageDialog(null, 
                "Invalid phone format. Use +213 followed by 9 digits (e.g., +213661234567).", 
                "Phone Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // checking if the rest are digits
        if (!phone.substring(4).matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, 
                "Phone must have exactly 9 digits after +213.", 
                "Phone Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, 
                "Name must contain only letters and spaces (e.g., lyna Doe). No numbers or special chars!", 
                "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (name.trim().length() < 2 || name.trim().length() > 50) {
            JOptionPane.showMessageDialog(null, 
                "Name must be 2-50 characters long!", 
                "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Address cannot be empty. Enter street, city, and ZIP (e.g., 123 Main St, Algiers 16000).", 
                "Address Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (address.trim().length() < 10) {
            JOptionPane.showMessageDialog(null, 
                "Address too short. Include at least street and city (min 10 chars).", 
                "Address Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // email
    public static boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Email cannot be empty.", 
                "Email Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        if (!email.matches(regex)) {
            JOptionPane.showMessageDialog(null, 
                "Invalid email format. Use something like user@example.com.", 
                "Email Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

  }
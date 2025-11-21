// coorect form with user
package com.bookstore.utils;
import javax.swing.JOptionPane;

public class InputValideator {
	public static boolean validatePhone(String phone) {
        if (!phone.startsWith("+213") || phone.length() != 13) {
            JOptionPane.showMessageDialog(null, "Invalid phone format. Use +213XXXXXXXXX", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
}

//Add more: e.g., validateName(String name) { return name.matches("[a-zA-Z ]+"); }
public static boolean validateName(String name) {
    if (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
        JOptionPane.showMessageDialog(null, "Name must contain only letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}

public static boolean validateAddress(String address) {
    if (address == null || address.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}
}
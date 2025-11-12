package com.bookstore.view;

import javax.swing.*;
import java.awt.*;

public class OrderFormPanel extends JPanel {
	public OrderFormPanel(MainFrame frame) {
		
		 setLayout(null);
	        setBackground(new Color(250, 250, 245));

	        // ---------- HEADER ----------
	        JLabel logo = new JLabel("📖 Story time★");
	        logo.setFont(new Font("Segoe UI", Font.ITALIC, 20));
	        logo.setBounds(40, 20, 200, 30);
	        add(logo);

	        JButton backBtn = new JButton("← Back");
	        backBtn.setFocusPainted(false);
	        backBtn.setContentAreaFilled(false);
	        backBtn.setBorderPainted(false);
	        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        backBtn.setBounds(40, 60, 100, 25);
	        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        add(backBtn);

	        // --- Icônes à droite (Search, Wishlist, Cart) ---
	        JButton searchBtn = new JButton("🔍");
	        JButton favBtn = new JButton("♡");
	        JButton cartBtn = new JButton("🛒");

	        JButton[] rightBtns = {searchBtn, favBtn, cartBtn};
	        int x = 800;
	        for (JButton b : rightBtns) {
	            b.setFocusPainted(false);
	            b.setContentAreaFilled(false);
	            b.setBorderPainted(false);
	            b.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	            b.setBounds(x, 25, 50, 30);
	            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            add(b);
	            x += 45;
	        }

	        // ---------- TITRE PRINCIPAL ----------
	        JLabel title = new JLabel("YOUR ORDER");
	        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
	        title.setBounds(380, 130, 200, 30);
	        add(title);

	        // ---------- FORMULAIRE ----------
	        JPanel formPanel = new JPanel();
	        formPanel.setLayout(new GridLayout(4, 2, 10, 15));
	        formPanel.setBackground(Color.WHITE);
	        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	        formPanel.setBounds(300, 180, 350, 180);
	        add(formPanel);

	        JLabel firstNameLabel = new JLabel("FIRST NAME");
	        JTextField firstNameField = new JTextField();

	        JLabel lastNameLabel = new JLabel("LAST NAME");
	        JTextField lastNameField = new JTextField();

	        JLabel phoneLabel = new JLabel("PHONE NUMBER");
	        JTextField phoneField = new JTextField("+213");

	        JLabel addressLabel = new JLabel("DELIVERY ADDRESS");
	        JTextField addressField = new JTextField();

	        formPanel.add(firstNameLabel);
	        formPanel.add(firstNameField);
	        formPanel.add(lastNameLabel);
	        formPanel.add(lastNameField);
	        formPanel.add(phoneLabel);
	        formPanel.add(phoneField);
	        formPanel.add(addressLabel);
	        formPanel.add(addressField);

	        // ---------- BOUTONS ----------
	        JButton cancelBtn = new JButton("CANCEL");
	        cancelBtn.setBackground(new Color(150, 150, 150));
	        cancelBtn.setForeground(Color.WHITE);
	        cancelBtn.setFocusPainted(false);
	        cancelBtn.setBounds(350, 400, 120, 35);
	        add(cancelBtn);
	        cancelBtn.addActionListener(e -> frame.navigateTo("CART"));


	        JButton checkoutBtn = new JButton("CHECKOUT");
	        checkoutBtn.setBackground(new Color(90, 140, 70));
	        checkoutBtn.setForeground(Color.WHITE);
	        checkoutBtn.setFocusPainted(false);
	        checkoutBtn.setBounds(500, 400, 120, 35);
	        add(checkoutBtn);
	    }

	   
	    
	} 
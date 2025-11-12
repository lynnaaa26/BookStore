// what u see
package com.bookstore.view;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame  {
	
	// cardLayout allows switching between diff panels like pages
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	// construct the main window applications screen 
	
	public MainFrame() {
		setTitle("Bookstore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null); // centre a window
		
		
		cardLayout = new CardLayout(); // create a new instance of the cardlayout class let u stack multiple panels
		mainPanel = new JPanel(cardLayout); // create a new jpanel means it special panel can hold multiple other  panels but will show only one at time 
		
		
		mainPanel.add(new HomePanel(this),"HOME"); 
		mainPanel.add(new BookPanel(this),"BOOKS"); 
		mainPanel.add(new CartPanel(this),"CART");
		mainPanel.add(new WishlistPanel(this),"WISHLIST");
		mainPanel.add(new OrderFormPanel(this),"ORDERFORM");
		mainPanel.add(new SearchPanel(this),"SEARCH");
		
		
		
		setContentPane(mainPanel);
		navigateTo("HOME");
		
	}
	
	public void navigateTo(String panelName) {
		// switching based on the given name 
		cardLayout.show(mainPanel, panelName);
	}
	
	public static void main (String[] args) {
		SwingUtilities.invokeLater( () -> new MainFrame().setVisible(true));
	}
	


}

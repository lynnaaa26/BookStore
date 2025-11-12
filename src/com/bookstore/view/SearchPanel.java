package com.bookstore.view;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    public SearchPanel(MainFrame frame) {
    	
    	
    	 setBackground(new Color(250, 250, 245)); 
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //--------- search bar + categories --------------
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS)); // vertical stack

        // search bar panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        JTextField searchField = new JTextField("Search books...");
        JButton searchBtn = new JButton("🔍");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        northPanel.add(searchPanel);
        northPanel.add(Box.createVerticalStrut(10)); // spacing

        //  categories
        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel catTitle = new JLabel("Find your next favorite story...");
        catTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        catPanel.add(catTitle);

        String[] categories = {"Classics", "Horror", "History", "Fiction", "Self-help",
                               "Romance", "Religion", "Biography", "Science", "Adventure"};
        for (String c : categories) {
            JButton catBtn = new JButton(c);
            catBtn.setPreferredSize(new Dimension(95, 25)); // 
            catPanel.add(catBtn);
        }
        northPanel.add(catPanel);

        add(northPanel, BorderLayout.NORTH);

        //---------------- best sellers ---------------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bestLabel = new JLabel("★ Best Sellers of the Month ★");
        bestLabel.setFont(new Font("Serif", Font.BOLD, 20));
        bestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bestLabel);
        centerPanel.add(Box.createVerticalStrut(15));

        //  books
        JPanel booksContainer = new JPanel(new GridLayout(1, 2, 20, 0));

        String[][] bestsellers = {
                {"The Yellow Wallpaper", "1000 DZD"},
                {"To Kill A Mockingbird", "1500 DZD"}
        };

        for (String[] b : bestsellers) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
            bookPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            bookPanel.setBackground(Color.WHITE);
            bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.setPreferredSize(new Dimension(90, 150)); 

            // image placeholder (smaller)
            JPanel imagePlaceholder = new JPanel();
            imagePlaceholder.setPreferredSize(new Dimension(50, 50)); 
            imagePlaceholder.setBackground(Color.LIGHT_GRAY);
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(imagePlaceholder);

            JLabel titleLabel = new JLabel(b[0]);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(titleLabel);

            JLabel priceLabel = new JLabel(b[1]);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(priceLabel);

            JLabel ratingLabel = new JLabel("★★★★★");
            ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(ratingLabel);

            JButton addBtn = new JButton("Add");
            addBtn.setFont(new Font("SansSerif", Font.PLAIN, 10)); //
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(addBtn);

            booksContainer.add(bookPanel);
        }

        centerPanel.add(booksContainer);
        centerPanel.add(Box.createVerticalStrut(20));
        add(centerPanel, BorderLayout.CENTER);

        //----------------back button ----------------
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backBtn = new JButton("← Back to Home");
        backBtn.addActionListener(e -> frame.navigateTo("HOME"));
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }
}

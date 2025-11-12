package com.bookstore.view;

import javax.swing.*;
import java.awt.*; // for layout managers

public class HomePanel extends JPanel {

    public HomePanel(MainFrame mainFrame) {
    	
    	

    	 setBackground(new Color(250, 250, 245)); 
        setLayout(new BorderLayout(20, 20));
        // add empty borders 
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //------------------- top panel--------------------------
        JPanel topPanel=new JPanel(new BorderLayout());
        topPanel.setOpaque(false);// transparent background
        
        JLabel header = new JLabel(" 📖 Story time ★", SwingConstants.LEFT);
        header.setFont(new Font("serif", Font.BOLD, 26)); //  font style and size
        topPanel.add(header, BorderLayout.WEST); // place at top little to the left

        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightButtonsPanel.setOpaque(false);
        
        JButton search= new JButton("🔍");
        search.addActionListener(e-> mainFrame.navigateTo("SEARCH"));
        rightButtonsPanel.add(search); 
        
        
        
        JButton wishlistButton = new JButton("❤️");
        wishlistButton.addActionListener(e -> mainFrame.navigateTo("WISHLIST"));
        rightButtonsPanel.add(wishlistButton);
        
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);
        add(topPanel,BorderLayout.NORTH);
        
        JButton cartButton = new JButton("🛒");
        cartButton.addActionListener(e -> mainFrame.navigateTo("CART"));
        rightButtonsPanel.add(cartButton);
        
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        
        //------------------ main content ---------------------
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // left and right

        //-----------------left side ---------------------
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // vertical 
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel subHeader = new JLabel("Find your next favorite story !");
        subHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
        leftPanel.add(subHeader);
        leftPanel.add(Box.createVerticalStrut(10)); //add vertical spacing to a container [1]

        // book 
        String[][] books = {
                {"Fiction", "Cloud Atlas", "1200 DZD","cloud.jpg"},
                {"Classic", "The Yellow Wallpaper", "1000 DZD" ,"yellow.jpg"},
                {"Mystery", "The Girl on the Train", "1600 DZD","The_Girl_on_the_Train.jpg"},
                {"Romance", "Sense and Sensibility", "1300 DZD","sense.jpg"},
        };

        //container to group books by rows
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS));// vertical

        // loop through books two by two so we can get 2 per line
        for (int i = 0; i < books.length; i += 2) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 2, 20, 20)); // 2 books per row

            for (int j = i; j < i + 2 && j < books.length; j++) { // i+2 so it adds 2 books with the condition to make sure we don't get through the last  book if it's odd
                String[] b = books[j];

                JPanel bookPanel = new JPanel(); // we create a new jpanel that will represent one book 
                bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS)); // the content of the book will vertical
                bookPanel.setBorder(BorderFactory.createTitledBorder(b[0]));// border with a title around the panel
                bookPanel.setBackground(Color.WHITE);

                // image placeholder
                JLabel imageLabel = new JLabel();
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imageLabel.setPreferredSize(new Dimension(100, 140));
                imageLabel.setMaximumSize(new Dimension(100, 140));
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                
                
                ImageIcon icon = null;
                try {
                    icon = new ImageIcon(getClass().getResource("/images/" + b[3]));
                } catch (Exception e) {
                    System.out.println("Image not found: " + b[3]);
                }

                if (icon != null && icon.getIconWidth() > 0) {
                    Image scaledImage = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                    imageLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    imageLabel.setText("No Image");
                    imageLabel.setBackground(Color.LIGHT_GRAY);
                    imageLabel.setOpaque(true);
                    imageLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                }


                // book info
                JLabel title = new JLabel(b[1]);
                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookPanel.add(title);

                JLabel price = new JLabel(b[2]);
                price.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookPanel.add(price);

                // "Add to Cart" button
                JButton addBtn = new JButton("Add to Cart");
                addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookPanel.add(addBtn);

                rowPanel.add(bookPanel);
            }

            //  adding each row to the main container
            booksContainer.add(rowPanel);
            booksContainer.add(Box.createVerticalStrut(10)); // spacing between rows
        }

        //  add the grouped container to the left panel
        leftPanel.add(booksContainer);

        //---------------right side------------------
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Best sellers of the Month"));

        String[][] bestsellers = {
                {"The Yellow Wallpaper", "1000 DZD"},
                {"To Kill A Mockingbird", "1500 DZD"}
        };

        for (String[] b : bestsellers) {
            JPanel item = new JPanel();
            item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
            item.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            item.setBackground(Color.WHITE);

            // image placeholder
            JPanel imagePlaceholder = new JPanel();
            imagePlaceholder.setPreferredSize(new Dimension(100, 140));
            imagePlaceholder.setMaximumSize(new Dimension(100, 140));
            imagePlaceholder.setBackground(Color.LIGHT_GRAY);
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            item.add(imagePlaceholder);

            JLabel titleLabel = new JLabel(b[0]);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            item.add(titleLabel);

            JLabel priceLabel = new JLabel(b[1]);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            item.add(priceLabel);

            JLabel ratingLabel = new JLabel("★★★★★");
            ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            item.add(ratingLabel);

            rightPanel.add(item);
            rightPanel.add(Box.createVerticalStrut(10)); // spacing
        }

        //Add both sides to main content 
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);

        add(contentPanel, BorderLayout.CENTER);
        
        
       

    
    }
}

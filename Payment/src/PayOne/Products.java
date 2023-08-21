 package PayOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Products extends JFrame {
    private JLabel titleLabel;
    private JButton buyButton;
    private JLabel backgroundLabel;
	private JButton backButton;

    public Products() {
        setTitle("Product Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);   
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
 
        titleLabel = new JLabel("Product Display Page");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 25));
        titleLabel.setBounds(550, 20, 300, 30);
        add(titleLabel);
        
        backButton = new JButton("Back");
        backButton.setBounds(300, 550, 150, 30);
        Font backbuttonFont = new Font("Tahoma", Font.BOLD, 15);
        backButton.setFont(backbuttonFont);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.orange);
        add(backButton);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Create and configure the "Buy Now" button
        buyButton = new JButton("Buy Now");
        buyButton.setBounds(140, 550, 150, 30);
        Font buttonFont = new Font("Tahoma", Font.BOLD, 15);
        buyButton.setFont(buttonFont);
        buyButton.setBackground(Color.ORANGE); // Set background color to orange
        buyButton.setForeground(Color.WHITE); // Set text color to white

        // Add an action listener to the "Buy Now" button
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the payment page
                SwingUtilities.invokeLater(() -> new PaymentOptions());
            }
        });

        add(buyButton);

        backgroundLabel = new JLabel(new ImageIcon("IPhone.jpg"));
        backgroundLabel.setBounds(0, 0, 1365, 702);
        add(backgroundLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Products());
    }
}




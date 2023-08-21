package PayOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PaymentOptions extends JFrame {
    private JLabel titleLabel;
    private JLabel paymentLabel;
    private JButton creditCardButton;
    private JButton debitCardButton;
    private JButton upiButton;
    private JButton giftCardButton;
   
    private JLabel backgroundLabel;
	private JButton backButton;

    public PaymentOptions() {
        setTitle("Payment Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 25));
        titleLabel.setBounds(550, 20, 200, 30);
        add(titleLabel);

//        paymentLabel = new JLabel("Select payment method");
//        paymentLabel.setBounds(540, 10, 200, 30);
//        add(paymentLabel);
//        paymentLabel.setFont(new Font("Tahoma",Font.BOLD,16));
//        paymentLabel.setForeground(Color.WHITE);

        creditCardButton = new JButton("Credit Card");
        creditCardButton.setBounds(620, 170, 110, 27);
        add(creditCardButton);

        debitCardButton = new JButton("Debit Card");
        debitCardButton.setBounds(620, 220, 110, 27);
        add(debitCardButton);

        upiButton = new JButton("UPI");
        upiButton.setBounds(620, 270, 110, 27);
        add(upiButton);

        giftCardButton = new JButton("Gift Card");
        giftCardButton.setBounds(620, 320, 110, 27);
        add(giftCardButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(620, 380, 110, 27);
        backButton.setBackground(Color.yellow);
       
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("payment image.jpeg").getImage();
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Add action listeners to payment method buttons
        creditCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SwingUtilities.invokeLater(() -> new CreditCard());
            }
        });

        debitCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SwingUtilities.invokeLater(() -> new DebitCard());
            }
        });

        upiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SwingUtilities.invokeLater(() -> new UPI());
            }
        });

        giftCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              SwingUtilities.invokeLater(()->new gifts());
            }
        });

       
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentOptions());
    }
}

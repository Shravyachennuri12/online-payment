package PayOne;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CreditCard extends JFrame {

    private JLabel titleLabel;
    private JLabel CreditCardBankLabel;
    private JLabel CreditCardnumberLabel;
    private JLabel CreditCardCVVlabel;
    private JLabel CreditCardExpiryDateLabel;
    private JLabel SlashSymbolLabel;
    private JTextField CreditCardBankField;
    private JTextField CreditCardNumberField1;
    private JTextField CreditCardNumberField2;
    private JTextField CreditCardNumberField3;
    private JTextField CreditCardNumberField4;
    private JTextField CreditCardCVVField;
    private JTextField CreditCardExpiryDate1Field;
    private JTextField CreditCardExpiryDate2Field;
    private JButton ConfirmButton;
    private JButton backButton;
    private JLabel backgroundLabel;
	

    public CreditCard() {
        setTitle("Creditcard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        titleLabel = new JLabel("Enter Credit Card Details");
        titleLabel.setFont(new Font("TAHOMA", Font.BOLD, 22));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(560, 20, 400, 30);
        add(titleLabel);

        CreditCardBankLabel = new JLabel("CreditCard Bank Name:");
        CreditCardBankLabel.setBounds(480, 70, 220, 23);
        CreditCardBankLabel.setForeground(Color.white);
        add(CreditCardBankLabel);

        CreditCardBankField = new JTextField();
        CreditCardBankField.setBounds(625, 70, 220, 22);
        add(CreditCardBankField);

        CreditCardnumberLabel = new JLabel("Credit Card Number:");
        CreditCardnumberLabel.setBounds(490, 120, 220, 23);
        CreditCardnumberLabel.setForeground(Color.white);
        add(CreditCardnumberLabel);

        CreditCardNumberField1 = new JTextField();
        CreditCardNumberField1.setBounds(625, 120, 40, 22);
        add(CreditCardNumberField1);

        CreditCardNumberField2 = new JTextField();
        CreditCardNumberField2.setBounds(670, 120, 40, 22);
        add(CreditCardNumberField2);

        CreditCardNumberField3 = new JTextField();
        CreditCardNumberField3.setBounds(715, 120, 40, 22);
        add(CreditCardNumberField3);

        CreditCardNumberField4 = new JTextField();
        CreditCardNumberField4.setBounds(760, 120, 40, 22);
        add(CreditCardNumberField4);

        CreditCardCVVlabel = new JLabel("CreditCard CVV:");
        CreditCardCVVlabel.setBounds(500, 170, 220, 20);
        CreditCardCVVlabel.setForeground(Color.white);
        add(CreditCardCVVlabel);

        CreditCardCVVField = new JTextField();
        CreditCardCVVField.setBounds(625, 170, 30, 22);
        add(CreditCardCVVField);

        CreditCardExpiryDateLabel = new JLabel("Expiry Date(MM/YY):");
        CreditCardExpiryDateLabel.setBounds(480, 220, 220, 20);
        CreditCardExpiryDateLabel.setForeground(Color.white);
        add(CreditCardExpiryDateLabel);
        
        SlashSymbolLabel =new JLabel("/");
        SlashSymbolLabel.setBounds(656,220,200,20);
        Font slashFont = new Font("Tahoma", Font.BOLD,15);
        SlashSymbolLabel.setFont(slashFont);
        SlashSymbolLabel.setForeground(Color.white);
        add(SlashSymbolLabel);
     
        CreditCardExpiryDate1Field = new JTextField();
        CreditCardExpiryDate1Field.setBounds(625, 220, 30, 22);
        add(CreditCardExpiryDate1Field);

        CreditCardExpiryDate2Field = new JTextField();
        CreditCardExpiryDate2Field.setBounds(665, 220, 30, 22);
        add(CreditCardExpiryDate2Field);

        ConfirmButton = new JButton("Confirm Payment");
        ConfirmButton.setBounds(615, 280, 140, 25);
        add(ConfirmButton);

        backButton = new JButton("Back");
        backButton.setBounds(635, 325, 100, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bankName = CreditCardBankField.getText();
                String cardNumber1 = CreditCardNumberField1.getText();
                String cardNumber2 = CreditCardNumberField2.getText();
                String cardNumber3 = CreditCardNumberField3.getText();
                String cardNumber4 = CreditCardNumberField4.getText();
                String cvv = CreditCardCVVField.getText();
                String expiryMonth = CreditCardExpiryDate1Field.getText();
                String expiryYear = CreditCardExpiryDate2Field.getText();
                String expiryDate = expiryMonth + "/" + expiryYear;

                String combinedCardNumber = cardNumber1 + cardNumber2 + cardNumber3 + cardNumber4;

                boolean isValid = isValidDetailsFromDatabase(bankName, combinedCardNumber, cvv, expiryDate);

                if (isValid) {
                    JOptionPane.showMessageDialog(CreditCard.this, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(CreditCard.this, "Invalid credit card details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backgroundLabel = new JLabel();
        Image backgroundImage = new ImageIcon("credit card.jpg").getImage();
        ImageIcon resizedImage = new ImageIcon(backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(resizedImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        setVisible(true);
    }

    private boolean isValidDetailsFromDatabase(String bankName, String cardNumber, String cvv, String expiryDate) {
        Connection connection = null;
        boolean isValid = false;

        try {
            String query = "SELECT * FROM credit_cards WHERE credit_bank_name = ? AND credit_card_number = ? AND credit_card_cvv = ? AND credit_card_expiry_date = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "admin123");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bankName);
            statement.setString(2, cardNumber);
            statement.setString(3, cvv);
            statement.setString(4, expiryDate);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreditCard());
    }
}
